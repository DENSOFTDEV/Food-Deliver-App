package com.densoft.fooddelivery.callback;

import com.densoft.fooddelivery.model.BestDealModel;
import com.densoft.fooddelivery.model.CategoryModel;

import java.util.List;

public interface ICategoryCallBackListener {

    void onCategoryLoadSuccess(List<CategoryModel> categoryModels);

    void onCategoryLoadFailed(String message);
}
