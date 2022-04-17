package com.example.anime.ui.account;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.anime.BlogActivity;
import com.example.anime.EnterScreen;
import com.example.anime.R;

import java.util.Random;
import java.util.zip.Inflater;

public class AccountFragment extends Fragment {


    private LinearLayout animHolder;
    private Button  all_anim;
    private ImageButton stats, settings;
    private ImageView logout, profileImg;
    private TextView name, status;
    private LayoutInflater infl;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background));

        getViews(root);
        clickListeners();

        for(int i = 0; i < 5; i ++){
            View view = infl.inflate(R.layout.preview_card, null, false);
            LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0,30,0);
            view.setLayoutParams(layoutParams);

            TextView title = view.findViewById(R.id.title_name);
            title.setText(i + " anime");


            animHolder.addView(view);
        }

        return root;
    }

    private void clickListeners(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EnterScreen.class));
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent blogIntent = new Intent(getContext(), BlogActivity.class);
                startActivity(blogIntent);


            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getContext(), EnterScreen.class));
            }
        });
        all_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), EnterScreen.class));
            }
        });
    }

    private void getViews(View r){
        infl = getLayoutInflater();
        animHolder = r.findViewById(R.id.anim_list);

        profileImg = r.findViewById(R.id.profile_img);
        name = r.findViewById(R.id.name);
        status = r.findViewById(R.id.about_person);
        stats = r.findViewById(R.id.stats_but);
        settings = r.findViewById(R.id.settings_but);
        logout = r.findViewById(R.id.logout);
        all_anim = r.findViewById(R.id.anim_titles);

    }
}