package com.densoft.fooddelivery.ui.comments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.densoft.fooddelivery.model.CommentModel;
import com.densoft.fooddelivery.model.FoodModel;

import java.util.List;

public class CommentViewModel extends ViewModel {

    private MutableLiveData<List<CommentModel>> mutableLiveDataCommentList;

    public CommentViewModel() {
        mutableLiveDataCommentList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CommentModel>> getMutableLiveDataCommentList() {
        return mutableLiveDataCommentList;
    }

    public void setMutableLiveDataCommentList(List<CommentModel> commentList) {
        mutableLiveDataCommentList.setValue(commentList);
    }
}