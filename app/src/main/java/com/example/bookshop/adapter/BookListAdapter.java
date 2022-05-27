package com.example.bookshop.adapter;

import android.graphics.BitmapFactory;
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

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> implements Serializable {
private List<BookItem> list;
private ItemListener itemListener;

public BookListAdapter() {
        list = new ArrayList<>();
        }

public void setList(List<BookItem> list){
        this.list = list;
        notifyDataSetChanged();
        }
public BookItem getItem(int position){
        return list.get(position);
        }

public void setItemListener(ItemListener itemListener){
        this.itemListener =itemListener;
        }

@Override
public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookitem, parent, false);
        return new BookListViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        BookItem item = list.get(position);
        if(item == null) return;
//        try {
//            URL newurl = new URL(item.getBook().getImage());
//            holder.imgBook.setImageBitmap(BitmapFactory.decodeStream(newurl.openConnection().getInputStream()));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Picasso.get().load(item.getBook().getImage()).into(holder.imgBook);
        holder.title.setText(item.getBook().getTitle());
        holder.price.setText(Integer.toString((int)item.getPrice())+ " vnđ");
        }

@Override
public int getItemCount() {
        return list.size();
        }

public class BookListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView title, price, heart;
    private ImageView imgBook;
    public BookListViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.txtTitle);
        price = itemView.findViewById(R.id.txtPrice);
        heart = itemView.findViewById(R.id.txtHeart);
        imgBook = itemView.findViewById(R.id.imgBook);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(itemListener != null){
            itemListener.onItemClick(v, getAdapterPosition());
        }
    }
}

public interface ItemListener{
    void onItemClick(View view, int position);
}
}
