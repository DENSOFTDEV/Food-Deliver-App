package com.densoft.fooddelivery.ui.fooddetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.densoft.fooddelivery.common.Common;
import com.densoft.fooddelivery.model.CommentModel;
import com.densoft.fooddelivery.model.FoodModel;

public class FoodDetailViewModel extends ViewModel {

    private MutableLiveData<FoodModel> mutableLiveDataFood;
    private MutableLiveData<CommentModel> mutableLiveDataComment;

    public FoodDetailViewModel() {
        mutableLiveDataComment = new MutableLiveData<>();
    }

    public void setMutableLiveDataComment(CommentModel commentModel) {
        if (mutableLiveDataComment != null) {
            mutableLiveDataComment.setValue(commentModel);
        }
    }

    public MutableLiveData<CommentModel> getMutableLiveDataComment() {
        return mutableLiveDataComment;
    }

    public MutableLiveData<FoodModel> getMutableLiveDataFood() {
        if (mutableLiveDataFood == null) {
            mutableLiveDataFood = new MutableLiveData<>();
            mutableLiveDataFood.setValue(Common.selectedFood);
        }
        return mutableLiveDataFood;
    }

    public void setMutableLiveDataFood(FoodModel foodModel) {
        if (mutableLiveDataFood != null)
            mutableLiveDataFood.setValue(foodModel);
    }
}