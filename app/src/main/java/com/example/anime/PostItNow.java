package com.example.anime;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.anime.Constructors.SliderItem;
import com.example.anime.Constructors.SliderItemPost;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class PostItNow extends AppCompatActivity {


    private List<Uri> paths = new ArrayList<>();
    private SliderView sliderView;
    private SliderAdapterForChosenImages adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_it_now);


        Bundle extras = getIntent().getExtras();
        if(getIntent().getExtras()!=null) {
            int size = extras.getInt("size");
            for(int i = 0; i < size;  i++){
                paths.add(Uri.parse(extras.getString(String.valueOf(i))));
            }
        }
        getViews();
        renewItems();
    }
    private void getViews(){
        sliderView = findViewById(R.id.pagerOfChosen);
        adapter = new SliderAdapterForChosenImages(PostItNow.this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }
    private void renewItems() {
        List<SliderItemPost> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < paths.size(); i++) {
            SliderItemPost sliderItem = new SliderItemPost();
            sliderItem.setUri(paths.get(i));
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }
}