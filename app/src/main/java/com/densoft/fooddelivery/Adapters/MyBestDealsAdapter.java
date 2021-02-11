package com.densoft.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.asksira.loopingviewpager.LoopingViewPager;
import com.bumptech.glide.Glide;
import com.densoft.fooddelivery.R;
import com.densoft.fooddelivery.model.BestDealModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.http.Query;

public class MyBestDealsAdapter extends LoopingPagerAdapter<BestDealModel> {

    Context context;
    private final List<? extends BestDealModel> itemList;
    private final boolean isInfinite;

    @BindView(R.id.img_best_deal)
    ImageView img_best_deal;

    @BindView(R.id.txt_best_deal)
    TextView txt_best_deal;

    Unbinder unbinder;

    public MyBestDealsAdapter(@NotNull Context context, @NotNull List<? extends BestDealModel> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
        this.context = context;
        this.itemList = itemList;
        this.isInfinite = isInfinite;
    }


    @Override
    protected void bindView(@NotNull View view, int i, int i1) {
        unbinder = ButterKnife.bind(this,view);
        //set data
        Glide.with(view).load(itemList.get(i).getImage()).into(img_best_deal);
        txt_best_deal.setText(itemList.get(i).getName());
    }

    @NotNull
    @Override
    protected View inflateView(int i, @NotNull ViewGroup viewGroup, int i1) {
        return LayoutInflater.from(context).inflate(R.layout.layout_best_deal_item,viewGroup,false);
    }
}
