package com.example.prm391_pe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prm391_pe.db.DataBase;
import com.example.prm391_pe.db.Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyCustomAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    EditText ed_number;
    EditText ed_lineCount;
    EditText ed_customerName;
    EditText ed_date;
    String numberOrder;
    private List<Order> orderList;
    List<Order> neworderList;

    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewData();
        orderList = DataBase.getInstance(getApplicationContext()).orderDAO().getAll();
        recyclerView = findViewById(R.id.rv_result);

        ((Button) findViewById(R.id.bt_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = ed_number.getText().toString();
                String date = ed_date.getText().toString();
                int lineCount = SetLineCount(ed_lineCount.getText().toString());
                String customerName = ed_customerName.getText().toString();
                Order newOrder = new Order(number, date, lineCount, customerName);
                DataBase.getInstance(getApplicationContext()).orderDAO().inser(newOrder);
                updateOrderList();
            }
        });
        ((Button) findViewById(R.id.bt_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = ed_number.getText().toString();
                Order deleteOrder = DataBase.getInstance(getApplicationContext()).orderDAO().loadAllByOrder(number);
                if (deleteOrder != null) {
                    DataBase.getInstance(getApplicationContext()).orderDAO().delete(deleteOrder);
                }
                updateOrderList();

            }
        });
        ((Button) findViewById(R.id.bt_update)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = ed_number.getText().toString();
                String date = ed_date.getText().toString();
                int lineCount = SetLineCount(ed_lineCount.getText().toString());
                String customerName = ed_customerName.getText().toString();
                Order updateOrder = DataBase.getInstance(getApplicationContext()).orderDAO().loadAllByOrder(numberOrder);
                if (updateOrder != null) {
                    updateOrder = new Order(number, date, lineCount, customerName);
                    DataBase.getInstance(getApplicationContext()).orderDAO().update(updateOrder);
                }
                updateOrderList();

            }
        });
        ((Button) findViewById(R.id.bt_list)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_lineCount.getText() == null && ed_customerName.getText() == null && ed_number == null && ed_date == null) {
                    updateOrderList();
                }else{
                    orderList.clear();
                    int lineLoad = SetLineCount(ed_lineCount.getText().toString());
                    orderList.addAll(DataBase.getInstance(getApplicationContext()).orderDAO().loadOrderByProperty(ed_number.getText().toString(),ed_date.getText().toString(),lineLoad,ed_customerName.getText().toString()));
                    recyclerView.setAdapter(adapter);
                }
            }
        });
        adapter = new MyCustomAdapter(orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(MainActivity.this);
        recyclerView.setAdapter(adapter);

    }

    private int SetLineCount(String parseInt) {
        int line_count = 0;
        try {
            line_count = Integer.parseInt(parseInt);
        } catch (Exception ex) {
        }
        return line_count;
    }

    private void updateOrderList() {
        orderList.clear();
        orderList.addAll(DataBase.getInstance(getApplicationContext()).orderDAO().getAll());
        recyclerView.setAdapter(adapter);
//        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void getViewData() {

        ed_number = findViewById(R.id.edit_number);
        ed_date = findViewById(R.id.edit_date);
        ed_lineCount = findViewById(R.id.edit_lineCount);
        ed_customerName = findViewById(R.id.edit_customerName);
    }


    @Override
    public void onItemClick(Order order) {
        numberOrder = order.getNumber();
        ed_number.setText(order.getNumber().toString());
        ed_date.setText(order.getDate().toString());
        ed_lineCount.setText(String.valueOf(order.getLine_count()));
        ed_customerName.setText(order.getCustomer_name().toString());
    }
}