package com.densoft.fooddelivery.ui.foodlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.densoft.fooddelivery.common.Common;
import com.densoft.fooddelivery.model.FoodModel;

import java.util.List;

public class FoodListViewModel extends ViewModel {

    private MutableLiveData<List<FoodModel>> mutableLiveDataFoodList;

    public FoodListViewModel(){

    }

    public MutableLiveData<List<FoodModel>> getMutableLiveDataFoodList() {
        if (mutableLiveDataFoodList == null){
            mutableLiveDataFoodList = new MutableLiveData<>();
            mutableLiveDataFoodList.setValue(Common.categorySelected.getFoods());
        }
        return mutableLiveDataFoodList;
    }
}