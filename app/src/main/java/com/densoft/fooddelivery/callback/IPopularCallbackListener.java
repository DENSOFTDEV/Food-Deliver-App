package com.densoft.fooddelivery.callback;

import com.densoft.fooddelivery.model.PopularCategoryModel;

import java.util.List;

public interface IPopularCallbackListener {
    void onPopularLoadSuccess(List<PopularCategoryModel> popularCategoryModels);

    void onPopularLoadFailed(String message);
}
