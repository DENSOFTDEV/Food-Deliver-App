package com.densoft.fooddelivery.callback;

import com.densoft.fooddelivery.model.CommentModel;

import java.util.List;

public interface ICommentCallbackListener {
    void OnCommentLoadSuccess(List<CommentModel> commentModels);
    void OnCommentLoadFailed(String message);
}
