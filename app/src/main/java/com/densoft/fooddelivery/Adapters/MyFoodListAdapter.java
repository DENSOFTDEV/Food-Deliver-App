package com.densoft.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.densoft.fooddelivery.Database.CartDataSource;
import com.densoft.fooddelivery.Database.CartDatabase;
import com.densoft.fooddelivery.Database.CartItem;
import com.densoft.fooddelivery.Database.LocalCartDataSource;
import com.densoft.fooddelivery.EventBus.CounterCartEvent;
import com.densoft.fooddelivery.EventBus.FoodItemClick;
import com.densoft.fooddelivery.R;
import com.densoft.fooddelivery.callback.IRecyclerClickListener;
import com.densoft.fooddelivery.common.Common;
import com.densoft.fooddelivery.model.FoodModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MyFoodListAdapter extends RecyclerView.Adapter<MyFoodListAdapter.MyViewHolder> {

    private Context context;
    private List<FoodModel> foodModelList;
    private CompositeDisposable compositeDisposable;
    private CartDataSource cartDataSource;

    public MyFoodListAdapter(Context context, List<FoodModel> foodModelList) {
        this.context = context;
        this.foodModelList = foodModelList;
        this.compositeDisposable = new CompositeDisposable();
        this.cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(context).cartDAO());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FoodModel foodModel = foodModelList.get(position);

        Glide.with(context).load(foodModel.getImage()).into(holder.img_food_img);
        holder.txt_food_price.setText(new StringBuilder("$").append(foodModel.getPrice()));
        holder.txt_food_name.setText(new StringBuilder("").append(foodModel.getName()));

        //Event
        holder.setListener(new IRecyclerClickListener() {
            @Override
            public void onItemClickListener(View view, int pos) {
                Common.selectedFood = foodModel;
                Common.selectedFood.setKey(String.valueOf(pos));
                EventBus.getDefault().postSticky(new FoodItemClick(true, foodModel));
            }
        });

        holder.img_quick_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem = new CartItem();
                cartItem.setUid(Common.currentUser.getUid());
                cartItem.setUserPhone(Common.currentUser.getPhone());

                cartItem.setFoodId(foodModel.getId());
                cartItem.setFoodName(foodModel.getName());
                cartItem.setFoodImage(foodModel.getImage());
                cartItem.setFoodPrice(Double.valueOf(String.valueOf(foodModel.getPrice())));
                cartItem.setFoodQuantity(1);
                cartItem.setFoodExtraPrice(0.0);
                cartItem.setFoodAddon("Default");
                cartItem.setFoodSize("Default");


                compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                            Toast.makeText(context, "Add To Cart Success", Toast.LENGTH_SHORT).show();
                            //Here we will send a notify to home activity to update counter in cart
                            EventBus.getDefault().postSticky(new CounterCartEvent(true));
                        }, throwable -> {
                            Toast.makeText(context, "[CART ERROR]" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }));
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Unbinder unbinder;
        @BindView(R.id.txt_food_name)
        TextView txt_food_name;

        @BindView(R.id.txt_food_price)
        TextView txt_food_price;

        @BindView(R.id.img_food_image)
        ImageView img_food_img;

        @BindView(R.id.img_fav)
        ImageView img_fav;

        @BindView(R.id.img_quick_cart)
        ImageView img_quick_cart;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClickListener(v, getAdapterPosition());
        }
    }
}
