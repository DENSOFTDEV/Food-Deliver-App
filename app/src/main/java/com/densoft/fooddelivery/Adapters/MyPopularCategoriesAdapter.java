package com.densoft.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.densoft.fooddelivery.R;
import com.densoft.fooddelivery.model.PopularCategoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyPopularCategoriesAdapter extends RecyclerView.Adapter<MyPopularCategoriesAdapter.ViewHolder> {

    Context context;
    List<PopularCategoryModel> popularCategoryModelList;

    public MyPopularCategoriesAdapter(Context context, List<PopularCategoryModel> popularCategoryModelList) {
        this.context = context;
        this.popularCategoryModelList = popularCategoryModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_popular_categories_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularCategoryModel popularCategoryModel = popularCategoryModelList.get(position);

        Glide.with(context).load(popularCategoryModel.getImage()).into(holder.category_image);
        holder.txt_category_name.setText(popularCategoryModel.getName());
    }

    @Override
    public int getItemCount() {
        return popularCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Unbinder unbinder;
        @BindView(R.id.txt_category_name)
        TextView txt_category_name;

        @BindView(R.id.category_image)
        CircleImageView category_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
