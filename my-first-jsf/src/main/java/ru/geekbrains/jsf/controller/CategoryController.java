package ru.geekbrains.jsf.controller;

import ru.geekbrains.jsf.entity.Category;
import ru.geekbrains.jsf.repo.CategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository CategoryRepository;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String createCategory() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect-true";
    }

    public List<Category> getAllCategories() {
        return CategoryRepository.findAllCategories();
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect-true";
    }

    public void deleteCategory(Category category) {
        CategoryRepository.deleteCategoryById(category.getId());
    }

    public String saveCategory() {
        CategoryRepository.saveOrUpdate(category);
        return "/category.xhtml?faces-redirect-true";
    }
}
