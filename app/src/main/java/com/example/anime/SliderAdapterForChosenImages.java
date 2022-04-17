package com.example.anime;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anime.Constructors.SliderItem;
import com.example.anime.Constructors.SliderItemPost;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapterForChosenImages extends
        SliderViewAdapter<SliderAdapterForChosenImages.SliderAdapterCHImages> {

    private Context context;
    private List<SliderItemPost> mSliderItems = new ArrayList<>();

    public SliderAdapterForChosenImages(Context context) {
        this.context = context;
    }

    public void renewItems(List<SliderItemPost> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }
    public void changeItem(int position, Uri uri){
        this.mSliderItems.get(position).setUri(uri);
        notifyDataSetChanged();
    }

    public void addItem(SliderItemPost sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }
    public void deleteAll() {
        this.mSliderItems.removeAll(mSliderItems.subList(0,mSliderItems.size()));
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterCHImages onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_for_chosen, null);
        return new SliderAdapterCHImages(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterCHImages viewHolder, final int position) {

        SliderItemPost sliderItem = mSliderItems.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getUri())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterCHImages extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterCHImages(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.chosen_one);
            this.itemView = itemView;
        }
    }

}
