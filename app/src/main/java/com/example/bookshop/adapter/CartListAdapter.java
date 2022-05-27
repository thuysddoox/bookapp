package com.example.bookshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.R;
import com.example.bookshop.model.BookItem;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListViewHolder> implements Serializable {
private List<BookItem> list;

public CartListAdapter() {
        list = new ArrayList<>();
        }

public void setList(List<BookItem> list){
        this.list = list;
        notifyDataSetChanged();
        }
public BookItem getItem(int position){
        return list.get(position);
        }

@Override
public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new CartListViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        BookItem item = list.get(position);
        if(item == null) return;
        Picasso.get().load(item.getBook().getImage()).into(holder.imgBook);
        holder.title.setText(item.getBook().getTitle());
        holder.quantity.setText(Integer.toString((int)item.getQuantity()));
        holder.price.setText(Integer.toString((int)item.getPrice())+ " vnđ");
        }

@Override
public int getItemCount() {
        return list.size();
        }

public class CartListViewHolder extends RecyclerView.ViewHolder {
    private TextView title, price, quantity;
    private ImageView imgBook;

    public CartListViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.txtTitle);
        price = itemView.findViewById(R.id.txtPrice);
        quantity = itemView.findViewById(R.id.txtQuantity);
        imgBook = itemView.findViewById(R.id.imgBook);
    }
}
}

