package com.example.prm391_pe.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDAO {

    @Query("SELECT * FROM `Order`")
    List<Order> getAll();

//        @Query("SELECT * FROM `Order` WHERE OrderId IN (:orderId)")
//        List<Order> loadAllByIds(int[] orderId);

    @Query("SELECT * FROM `Order` WHERE Number IN (:orderNumber)")
    Order loadAllByOrder(String orderNumber);

    @Update
    void update(Order order);

    @Insert
    void insertAll(Order... orders);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inser(Order order);

    @Query("SELECT * FROM 'Order' WHERE number=:number OR date=:date OR line_count=:line_count OR customer_name=:customer_name")
    List<Order> loadOrderByProperty(String number,String date,int line_count,String customer_name);


    @Delete
    void delete(Order order);

}
