package com.densoft.fooddelivery.EventBus;

import com.densoft.fooddelivery.model.FoodModel;

public class FoodItemClick {

    private boolean success;
    private FoodModel foodModel;


    public FoodItemClick(boolean b, FoodModel foodModel) {
        this.success = b;
        this.foodModel = foodModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public FoodModel getFoodModel() {
        return foodModel;
    }

    public void setFoodModel(FoodModel foodModel) {
        this.foodModel = foodModel;
    }
}
