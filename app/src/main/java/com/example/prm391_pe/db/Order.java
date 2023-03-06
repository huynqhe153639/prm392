package com.example.prm391_pe.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Order {
//    @PrimaryKey()
//    @NonNull
//    @ColumnInfo(name = "OrderId")
//    private int id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Number")
    private String number;

    @ColumnInfo(name = "Date")
    private String date;

    @ColumnInfo(name = "Line_Count")
    private int line_count;

    @ColumnInfo(name = "Customer_Name")
    private String customer_name;


    public Order( String number, String date, int line_count, String customer_name) {
        this.number = number;
        this.date = date;
        this.line_count = line_count;
        this.customer_name = customer_name;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLine_count() {
        return line_count;
    }

    public void setLine_count(int line_count) {
        this.line_count = line_count;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
