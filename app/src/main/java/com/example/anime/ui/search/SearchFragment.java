package com.example.anime.ui.search;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.anime.Constructors.SpinnerAdapter;
import com.example.anime.R;
import com.example.anime.SliderAdapter;
import com.example.anime.Constructors.SliderItem;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    private SliderView sliderView;
    private SliderAdapter adapter;
    private LayoutInflater infl;
    private LinearLayout grid;
    private List<String> items = new ArrayList<String>();
    private Spinner spinner;
    private SpinnerAdapter spinnerAdapter;
    private float dpWidth, dpHeight;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background));
        getViews(root);




        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        renewItems();
        sliderView.startAutoCycle();

        grid = root.findViewById(R.id.grid_f);

        for(int i = 0; i < 6;  i++){
            View view = infl.inflate(R.layout.grid_item_ex, null, false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,10,0,0);
            layoutParams.height = DpSize(150);
            view.setLayoutParams(layoutParams);
            TextView views = view.findViewById(R.id.views);
            TextView series = view.findViewById(R.id.series);
            TextView about = view.findViewById(R.id.about_anime);
            TextView title = view.findViewById(R.id.title_name);
            ImageView img = view.findViewById(R.id.img_anime);
            if(i == 0){
                img.setImageDrawable(getResources().getDrawable(R.drawable.zero_two_ex));
                title.setText("Darling in the Franxx");
                about.setText("This story is about an otaku loser who died under the wheels of a car, and then came to his senses in the body of a newborn. As it turned out, he was reborn in another world, and now his name is Rudeus Greyrat. In order to survive and not repeat past mistakes, Rudeus decided to diligently study magic and the art of swordsmanship.");

            }else if(i == 1){
                Glide.with(getContext()).load("https://anixart.ru/assets/images/interesting/50360.jpg").into(img);
                title.setText("Mushoku Tensei");
                about.setText("This story is about an otaku loser who died under the wheels of a car, and then came to his senses in the body of a newborn. As it turned out, he was reborn in another world, and now his name is Rudeus Greyrat. In order to survive and not repeat past mistakes, Rudeus decided to diligently study magic and the art of swordsmanship.");

            }else if(i == 2){
                Glide.with(getContext()).load("https://otakukart.com/wp-content/uploads/2022/03/Marin.jpg").into(img);
                title.setText("My dress up Darling");
                about.setText("This story is about boy Wakana, who is interested in toy creating. But is SHE interesting for him?");
            }else if(i == 3){
                title.setText("My dress up Darling");
                about.setText("This story is about boy Wakana, who is interested in toy creating. But is SHE interesting for him?");

                Glide.with(getContext()).load("https://www.xtrafondos.com/wallpapers/my-dress-up-darling-9420.jpg").into(img);
            }else if(i == 4){
                title.setText("Takt Opus. Fate");
                about.setText("This is a multimedia project about classical music with really cool heroes.");

                Glide.with(getContext()).load("https://anixart.ru/assets/images/slider/29@1x.webp").into(img);
            }else if(i == 5){
                title.setText("Child of Kamiari Month");
                about.setText("Kanna is a 12-year-old girl, born of the gods. Her family has a mission to bring offerings from all over Japan to the gathering of the gods in Izumo. Kann has to complete the mission, hoping that she will be reunited with her dead game in the land of the Gods at the end of her journey.");

                Glide.with(getContext()).load("https://anixart.ru/assets/images/interesting/0f5d37da5236645c56b44806c7c2c0a6.jpg").into(img);
            }else if(i == 6){
                title.setText("My dress up Darling");
                about.setText("This story is about boy Wakana, who is interested in toy creating. But is SHE interesting for him?");

                Glide.with(getContext()).load("https://www.xtrafondos.com/wallpapers/my-dress-up-darling-9420.jpg").into(img);
            }
            views.setText((int)Math.floor(Math.random() * 1000000) + " views");
            series.setText((int)Math.floor(Math.random() * 60) + " series");

            grid.addView(view);

        }

        /*grid = root.findViewById(R.id.grid_f); grid.removeAllViews();
        int total = 6;
        int row = 2;
        int column = total / row;
        grid.setColumnCount(column);
        grid.setRowCount(row);

        for(int i =0, c = 0, r = 0; i < total; i++, r++)
        {
            if(r == grid.getRowCount())
            {
                r = 0;
                c++;
            }
            View view = infl.inflate(R.layout.grid_item_ex, null, false);
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int hei = (int) (150 * scale + 0.5f);
            int wid = (int) ((dpWidth-30) * scale + 0.5f);
            param.height = hei;
            param.width = wid;
            param.rightMargin = 5;
            param.topMargin = 5;
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams(param);
            TextView views = view.findViewById(R.id.views);
            TextView series = view.findViewById(R.id.series);
            TextView about = view.findViewById(R.id.about_anime);
            TextView title = view.findViewById(R.id.title_name);
            ImageView img = view.findViewById(R.id.img_anime);
            if(i == 0){
                img.setImageDrawable(getResources().getDrawable(R.drawable.zero_two_ex));
                title.setText("Darling in the Franxx");
                about.setText("This story is about an otaku loser who died under the wheels of a car, and then came to his senses in the body of a newborn. As it turned out, he was reborn in another world, and now his name is Rudeus Greyrat. In order to survive and not repeat past mistakes, Rudeus decided to diligently study magic and the art of swordsmanship.");

            }else if(i == 1){
                Glide.with(getContext()).load("https://anixart.ru/assets/images/interesting/50360.jpg").into(img);
                title.setText("Mushoku Tensei");
                about.setText("This story is about an otaku loser who died under the wheels of a car, and then came to his senses in the body of a newborn. As it turned out, he was reborn in another world, and now his name is Rudeus Greyrat. In order to survive and not repeat past mistakes, Rudeus decided to diligently study magic and the art of swordsmanship.");

            }else if(i == 2){
                Glide.with(getContext()).load("https://otakukart.com/wp-content/uploads/2022/03/Marin.jpg").into(img);
                title.setText("My dress up Darling");
                about.setText("This story is about boy Wakana, who is interested in toy creating. But is SHE interesting for him?");
            }else if(i == 3){
                title.setText("My dress up Darling");
                about.setText("This story is about boy Wakana, who is interested in toy creating. But is SHE interesting for him?");

                Glide.with(getContext()).load("https://www.xtrafondos.com/wallpapers/my-dress-up-darling-9420.jpg").into(img);
            }else if(i == 4){
                title.setText("Takt Opus. Fate");
                about.setText("This is a multimedia project about classical music with really cool heroes.");

                Glide.with(getContext()).load("https://anixart.ru/assets/images/slider/29@1x.webp").into(img);
            }else if(i == 5){
                title.setText("Child of Kamiari Month");
                about.setText("Kanna is a 12-year-old girl, born of the gods. Her family has a mission to bring offerings from all over Japan to the gathering of the gods in Izumo. Kann has to complete the mission, hoping that she will be reunited with her dead game in the land of the Gods at the end of her journey.");

                Glide.with(getContext()).load("https://anixart.ru/assets/images/interesting/0f5d37da5236645c56b44806c7c2c0a6.jpg").into(img);
            }else if(i == 6){
                title.setText("My dress up Darling");
                about.setText("This story is about boy Wakana, who is interested in toy creating. But is SHE interesting for him?");

                Glide.with(getContext()).load("https://www.xtrafondos.com/wallpapers/my-dress-up-darling-9420.jpg").into(img);
            }
            views.setText((int)Math.floor(Math.random() * 1000000) + " views");
            series.setText((int)Math.floor(Math.random() * 60) + " series");

            grid.addView(view);
        }*/


        return root;
    }

    private int DpSize(int dp){
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int sortCount = 0;
    private void getViews(View r){
        items.add("Popularity");
        items.add("Novelty");
        items.add("Views");
        items.add("Rating");

        infl = getLayoutInflater();
        sliderView = r.findViewById(R.id.imageSlider);
        grid = r.findViewById(R.id.grid_f);

        adapter = new SliderAdapter(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);

        spinner = r.findViewById(R.id.nice_spinner);
        spinnerAdapter = new SpinnerAdapter(getContext(), items);
        spinner.setAdapter(spinnerAdapter);
        initSpinner();

    }
    private void initSpinner() {


    }




    // When user click on Menu Item.
    // @return true if event was handled.

    private void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setName("My dress up darling");
            sliderItem.setDescription("My dress up darling is one of the best anime.");
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://otakukart.com/wp-content/uploads/2022/03/Marin.jpg");
            } else {
                sliderItem.setImageUrl("https://www.xtrafondos.com/wallpapers/my-dress-up-darling-9420.jpg");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    private void removeLastItem() {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    private void addNewItem() {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        adapter.addItem(sliderItem);
    }
}