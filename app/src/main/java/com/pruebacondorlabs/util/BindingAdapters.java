package com.pruebacondorlabs.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, final MutableLiveData<String> url, Drawable error) {
        url.observeForever(urls ->{
            Picasso.get().load(urls).error(error).into(view);
        });
    }


    @BindingAdapter("mutableText")
    public static void Text(final TextView textView, final MutableLiveData<String> date) {
        date.observeForever(s -> {
            if(date.getValue() != null) {
                textView.setText(date.getValue());
            }
        });
    }

    @BindingAdapter("mutableTextViewVisibility")
    public static void TextViewVisibility(final TextView textView,final MutableLiveData<Integer> date) {
        date.observeForever(aBoolean -> {
            if(aBoolean != null){
                textView.setVisibility(date.getValue());
            }

        });
    }

    @BindingAdapter("mutableVisibilityImage")
    public static void visibilityImage(ImageView imageView,MutableLiveData<Integer> image){
        image.observeForever(images ->{
            if(images != null) {
                imageView.setVisibility(images);
            }
        });
    }

    @BindingAdapter("mutableLinearLayoutVisibility")
    public static void LinearLayoutVisibility(LinearLayout linearLayout, MutableLiveData<Integer> date){
        date.observeForever(s -> {
            if(date != null) linearLayout.setVisibility(date.getValue());
        });
    }

}
