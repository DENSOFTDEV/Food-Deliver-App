package com.densoft.fooddelivery.ui.menu;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.densoft.fooddelivery.callback.ICategoryCallBackListener;
import com.densoft.fooddelivery.common.Common;
import com.densoft.fooddelivery.model.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel implements ICategoryCallBackListener {


    private MutableLiveData<List<CategoryModel>> categoryMutableList;
    private MutableLiveData<String> messageError = new MutableLiveData<>();
    private ICategoryCallBackListener categoryCallBackListener;

    public MenuViewModel() {
        categoryCallBackListener = this;
    }


    public MutableLiveData<List<CategoryModel>> getCategoryMutableList() {
        if (categoryMutableList == null) {
            categoryMutableList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadCategories();
        }
        return categoryMutableList;
    }

    private void loadCategories() {

        List<CategoryModel> templist = new ArrayList<>();
        DatabaseReference categoryRef = FirebaseDatabase.getInstance().getReference(Common.CATEGORY_REF);
        categoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemsnapshot : snapshot.getChildren()) {
                    CategoryModel categoryModel = itemsnapshot.getValue(CategoryModel.class);
                    categoryModel.setMenu_id(itemsnapshot.getKey());
                    templist.add(categoryModel);
                }
                categoryCallBackListener.onCategoryLoadSuccess(templist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                categoryCallBackListener.onCategoryLoadFailed(error.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onCategoryLoadSuccess(List<CategoryModel> categoryModels) {
        categoryMutableList.setValue(categoryModels);
    }

    @Override
    public void onCategoryLoadFailed(String message) {
        messageError.setValue(message);
    }
}