package com.ecommerce.project.service;

import com.ecommerce.project.model.category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
}