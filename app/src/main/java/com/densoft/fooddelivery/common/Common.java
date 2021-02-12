package com.densoft.fooddelivery.common;

import com.densoft.fooddelivery.model.CategoryModel;
import com.densoft.fooddelivery.model.UserModel;

public class Common {
    public static final String USER_REFERENCES = "Users";
    public static final String POPULAR_CATEGORIES_REF = "MostPopular";
    public static final String BEST_DEALS_REF = "BestDeals";
    public static final int DEFAULT_COLUMN_COUNT = 0;
    public static final int FULL_WIDTH_COLUMN = 1;
    public static final String CATEGORY_REF = "Category";
    public static UserModel currentUser;
    public static CategoryModel categorySelected;
}
