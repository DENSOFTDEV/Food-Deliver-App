package com.densoft.fooddelivery.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.densoft.fooddelivery.callback.IBestDealCallbackListener;
import com.densoft.fooddelivery.callback.IPopularCallbackListener;
import com.densoft.fooddelivery.common.Common;
import com.densoft.fooddelivery.model.BestDealModel;
import com.densoft.fooddelivery.model.PopularCategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements IPopularCallbackListener, IBestDealCallbackListener {

    private MutableLiveData<List<PopularCategoryModel>> popularList;
    private MutableLiveData<List<BestDealModel>> bestdealslist;
    private MutableLiveData<String> messageError;
    private IPopularCallbackListener popularCallbackListener;
    private IBestDealCallbackListener bestDealCallbackListener;

    public HomeViewModel() {
        popularCallbackListener = this;
        bestDealCallbackListener = this;
    }

    public MutableLiveData<List<BestDealModel>> getBestdealslist() {
        if (bestdealslist == null){
            bestdealslist = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadBestModelsList();
        }
        return bestdealslist;
    }

    private void loadBestModelsList() {
        List<BestDealModel> templist = new ArrayList<>();
        DatabaseReference bestDealsref = FirebaseDatabase.getInstance().getReference(Common.BEST_DEALS_REF);
        bestDealsref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemsnapshot: snapshot.getChildren()){
                    BestDealModel model = itemsnapshot.getValue(BestDealModel.class);
                    templist.add(model);
                }
                bestDealCallbackListener.onBestDealsLoadSuccess(templist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bestDealCallbackListener.onBestDealsLoadFailed(error.getMessage());
            }
        });
    }

    public MutableLiveData<List<PopularCategoryModel>> getPopularList() {
        if (popularList == null) {
            popularList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadPopularList();
        }
        return popularList;
    }

    private void loadPopularList() {
        List<PopularCategoryModel> tempList = new ArrayList<>();
        DatabaseReference popularRef = FirebaseDatabase.getInstance().getReference(Common.POPULAR_CATEGORIES_REF);
        popularRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    PopularCategoryModel model = itemSnapshot.getValue(PopularCategoryModel.class);
                    tempList.add(model);
                }
                popularCallbackListener.onPopularLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                popularCallbackListener.onPopularLoadFailed(error.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onPopularLoadSuccess(List<PopularCategoryModel> popularCategoryModels) {
        popularList.setValue(popularCategoryModels);

    }

    @Override
    public void onPopularLoadFailed(String message) {
        messageError.setValue(message);
    }

    @Override
    public void onBestDealsLoadSuccess(List<BestDealModel> bestDealModels) {
        bestdealslist.setValue(bestDealModels);
    }

    @Override
    public void onBestDealsLoadFailed(String message) {
        messageError.setValue(message);
    }
}