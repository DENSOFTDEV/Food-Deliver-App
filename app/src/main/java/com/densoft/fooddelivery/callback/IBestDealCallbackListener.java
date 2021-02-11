package com.densoft.fooddelivery.callback;

import com.densoft.fooddelivery.model.BestDealModel;
import com.densoft.fooddelivery.model.PopularCategoryModel;

import java.util.List;

public interface IBestDealCallbackListener {
    void onBestDealsLoadSuccess(List<BestDealModel> bestDealModels);

    void onBestDealsLoadFailed(String message);
}
