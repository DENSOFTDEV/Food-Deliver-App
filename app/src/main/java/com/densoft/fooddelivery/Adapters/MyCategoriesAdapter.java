package com.densoft.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.densoft.fooddelivery.EventBus.CategoryClick;
import com.densoft.fooddelivery.R;
import com.densoft.fooddelivery.callback.IRecyclerClickListener;
import com.densoft.fooddelivery.common.Common;
import com.densoft.fooddelivery.model.CategoryModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCategoriesAdapter extends RecyclerView.Adapter<MyCategoriesAdapter.ViewHolder> {

    Context context;
    List<CategoryModel> categoryModelList;

    public MyCategoriesAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public MyCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoriesAdapter.ViewHolder holder, int position) {
        CategoryModel model = categoryModelList.get(position);

        Glide.with(context).load(model.getImage()).into(holder.category_image);
        holder.category_name.setText(new StringBuilder(categoryModelList.get(position).getName()));

        //event
        holder.setListener(new IRecyclerClickListener() {
            @Override
            public void onItemClickListener(View view, int pos) {
                Common.categorySelected = categoryModelList.get(pos);
                EventBus.getDefault().postSticky(new CategoryClick(true,categoryModelList.get(pos)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Unbinder unbinder;

        @BindView(R.id.img_category)
        ImageView category_image;

        @BindView(R.id.txt_category)
        TextView category_name;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener) {
            this.listener = listener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClickListener(v,getAdapterPosition());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (categoryModelList.size() == 1)
            return Common.DEFAULT_COLUMN_COUNT;
        else{
            if (categoryModelList.size() % 2 == 0){
                return Common.DEFAULT_COLUMN_COUNT;
            }else{
                return (position > 1 && position == categoryModelList.size()-1) ? Common.FULL_WIDTH_COLUMN : Common.DEFAULT_COLUMN_COUNT;
            }
        }

    }
}
