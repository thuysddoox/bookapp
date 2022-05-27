package com.example.bookshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.DetailBookActivity;
import com.example.bookshop.R;
import com.example.bookshop.adapter.BookListAdapter;
import com.example.bookshop.adapter.SliderAdapter;
import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.BookItem;
import com.example.bookshop.model.ListBookResponse;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
public class FragmentHome extends Fragment implements BookListAdapter.ItemListener{
    private RecyclerView recyclerView;
    BookListAdapter bookListAdapter;
    SliderView sliderView;
    int[] images = {
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        sliderView = view.findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        BookItem item = bookListAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), DetailBookActivity.class);
        intent.putExtra("bookItem", item);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rview);
        bookListAdapter = new BookListAdapter();
        setListBook();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(bookListAdapter);
        bookListAdapter.setItemListener(this);
    }

    private void setListBook() {
        ApiService.apiService.getListBook().enqueue(new Callback<ListBookResponse>(){
            @Override
            public void onResponse(Call<ListBookResponse> call, Response<ListBookResponse> response) {
                if (response.body()!= null)
                bookListAdapter.setList(response.body().getData());
            }
            @Override
            public void onFailure(Call<ListBookResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setListBook();
    }
}
