package com.densoft.fooddelivery.EventBus;

import com.densoft.fooddelivery.model.CategoryModel;

public class CategoryClick {

    private boolean success;
    private CategoryModel categoryModel;

    public CategoryClick(boolean b, CategoryModel categoryModel) {
        this.success = b;
        this.categoryModel = categoryModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }
}
