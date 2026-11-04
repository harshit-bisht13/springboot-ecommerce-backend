package com.ecommerce.project.service;

import com.ecommerce.project.model.category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<category> categories = new ArrayList<>();

    @Override
    public List<category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(category category) {
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        category category = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Catergory not found"));

            categories.remove(category);
            return "Category " + categoryId + " deleted successfully";

    }

    @Override
    public category updateCategory(category category) {

        Optional<category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId() == category.getCategoryId())
                .findFirst();

        if (optionalCategory.isPresent()) {

            category existingCategory = optionalCategory.get();

            existingCategory.setCategoryName(category.getCategoryName());

            return existingCategory;

        } else {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category not found"
            );
        }
    }
}