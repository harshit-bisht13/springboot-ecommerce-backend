package com.ecommerce.project.service;
import com.ecommerce.project.model.category;
import java.util.List;
public interface CategoryService {
    List<category> getAllCategories();
    void createCategory(category category);
    String deleteCategory(Long categoryId);
}
