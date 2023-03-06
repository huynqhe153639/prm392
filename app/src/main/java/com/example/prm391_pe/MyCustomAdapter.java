package com.example.prm391_pe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm391_pe.db.Order;

import java.util.List;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.OrderHolder> {

    public interface OnItemClickListener {
        void onItemClick(Order order);
    }
    List<Order> orders;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public MyCustomAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
//        holder.tv_id.setText(String.valueOf(orders.get(position).getId()));
        holder.tv_customerName.setText(orders.get(position).getCustomer_name().toString());
        holder.tv_lineCount.setText(String.valueOf(orders.get(position).getLine_count()));
        holder.tv_date.setText(orders.get(position).getDate().toString());
        holder.tv_number.setText(orders.get(position).getNumber().toString());
//        holder.setOnItemClickListener(mListener);

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder {
//        public TextView tv_id;
        public TextView tv_customerName;
        public TextView tv_lineCount;
        public TextView tv_date;
        public TextView tv_number;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
//            tv_id = itemView.findViewById(R.id.tv_id);
            tv_customerName = itemView.findViewById(R.id.tv_customerName);
            tv_lineCount = itemView.findViewById(R.id.tv_lineCount);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_number = itemView.findViewById(R.id.tv_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Order item = orders.get(position);
                            mListener.onItemClick(item);
                        }
                    }
                }
            });
        }
    }
}
