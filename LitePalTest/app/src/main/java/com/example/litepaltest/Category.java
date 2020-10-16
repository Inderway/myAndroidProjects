package com.example.litepaltest;

/**
 * Created by ts on 20-10-15.
 */

//If we want to add a table named Category, just create it like Book
public class Category {
    private int id;
    private String categoryName;
    private int categoryCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

}
