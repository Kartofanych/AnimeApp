package com.example.anime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.smarteist.autoimageslider.SliderView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BlogActivity extends AppCompatActivity {

    private TextView name, status;
    private ImageView profileImg;

    private LinearLayout Holder;
    private LayoutInflater infl;
    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout bottomSheet, createPost;

    private Bitmap imageBitmap;
    private ArrayList<Bitmap> imgBitmaps = new ArrayList<>();
    private SliderAdapter adapterPosts;

    private FragmentStateAdapter pagerAdapter;
    private ViewPager2 pager;
    private TabLayout tabLayout;
    private int stat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        getWindow().setStatusBarColor(getResources().getColor(R.color.background));

        getViews();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*if(position == 0){
                    reviewBut.setBackgroundTintList(ContextCompat.getColorStateList(BlogActivity.this, R.color.background));
                    blogBut.setBackgroundTintList(ContextCompat.getColorStateList(BlogActivity.this, R.color.background_another));
                }else if(position == 1){
                    reviewBut.setBackgroundTintList(ContextCompat.getColorStateList(BlogActivity.this, R.color.background_another));
                    blogBut.setBackgroundTintList(ContextCompat.getColorStateList(BlogActivity.this, R.color.background));
                }*/
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int position) {
                stat = position;

                super.onPageScrollStateChanged(position);
            }
        });
        //LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addItem();
                Alert();
            }
        });
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BlogActivity.this, CreatePost2.class));
            }
        });
    }

    private int kol = 0;
    private ImageView imgView;
    private void addImg(LinearLayout lin){
        View adds = infl.inflate(R.layout.post_creating_img, null);
        ImageView imgPicker = adds.findViewById(R.id.alert_pick_up_file);
        imgView = adds.findViewById(R.id.img_view);
        imgPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(BlogActivity.this)
                        .crop()		//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });


        lin.addView(adds);
    }
    private void Alert(){
        pager.setCurrentItem(0);
        AlertDialog.Builder alert = new AlertDialog.Builder(BlogActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        View view = infl.inflate(R.layout.alert_create_post, null);
        TextView post = view.findViewById(R.id.alert_post);
        EditText alertTxt = view.findViewById(R.id.alert_txt);
        LinearLayout lin = view.findViewById(R.id.adds_holder);
        addImg(lin);

        alertTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    alertTxt.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    alertTxt.setBackgroundColor(getResources().getColor(R.color.background_another));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    alertTxt.setBackgroundColor(getResources().getColor(R.color.green));
                }else if(s.length() == 0){
                    alertTxt.setBackgroundColor(getResources().getColor(R.color.background_another));
                }
            }
        });


        alert.setView(view);
        //alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alertTxt.getText().toString() != null && imgBitmaps.size()>0){
                    v.setBackgroundColor(getResources().getColor(R.color.green));
                    addItem(imgBitmaps, alertTxt.getText().toString());
                    adapterPosts.deleteAll();
                    dialog.cancel();
                }
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Log.d("1", "1ffq");
            Uri image_uri = data.getData();

            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(image_uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            imgView.setImageBitmap(selectedImage);
            imageBitmap = selectedImage;
            imgBitmaps.add(selectedImage);

        }
    }
    private void addItem(ArrayList<Bitmap> imgBitmap, String txt){
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 20;
        View blogItem = infl.inflate(R.layout.blog_item,null, false);

        //SliderView sliderView = blogItem.findViewById(R.id.sliderBlog);


        RelativeLayout info_blog_holder = blogItem.findViewById(R.id.info_blog_holder);
        TextView txtBlog = blogItem.findViewById(R.id.blog_item_txt);
        ImageView img = blogItem.findViewById(R.id.blog_item_img);
        TextView raiting = blogItem.findViewById(R.id.raiting);
        ImageView up = blogItem.findViewById(R.id.raiting_up);
        ImageView down = blogItem.findViewById(R.id.raiting_down);
        RelativeLayout comments = blogItem.findViewById(R.id.blog_comments);



        info_blog_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blogIntent = new Intent(BlogActivity.this, MainWindow.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(profileImg, "profile_img_trans");
                blogIntent.putExtra("status", status.getText().toString());
                blogIntent.putExtra("name", name.getText().toString());

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(BlogActivity.this,
                        pairs);
                startActivity(blogIntent, options.toBundle());
            }
        });
        txtBlog.setText(txt);
        img.setImageBitmap(imgBitmap.get(0));
        raiting.setText(
                //String.valueOf(ThreadLocalRandom.current().nextInt(-100, 100 + 1))
                "0");
        blogItem.setLayoutParams(params);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up.setClickable(false);
                down.setClickable(true);
                up.setColorFilter(ContextCompat.getColor(BlogActivity.this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
                down.setColorFilter(ContextCompat.getColor(BlogActivity.this, R.color.backgroundSecond), android.graphics.PorterDuff.Mode.MULTIPLY);
                raiting.setTextColor(getResources().getColor(R.color.green));
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down.setClickable(false);
                up.setClickable(true);
                down.setColorFilter(ContextCompat.getColor(BlogActivity.this, R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                up.setColorFilter(ContextCompat.getColor(BlogActivity.this, R.color.backgroundSecond), android.graphics.PorterDuff.Mode.MULTIPLY);
                raiting.setTextColor(getResources().getColor(R.color.red));
            }
        });

        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        Holder = pager.findViewById(R.id.blog_items_holder);
        Holder.addView(blogItem);
    }
    private void getViews(){

        infl = getLayoutInflater();
        adapterPosts = new SliderAdapter(this);
        tabLayout = findViewById(R.id.tab_layout);
        pager = findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle());
        pager.setAdapter(pagerAdapter);
        bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        createPost = findViewById(R.id.createPost);
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        profileImg = findViewById(R.id.profile_img);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            name.setText(extras.getString("name"));
            status.setText(extras.getString("status"));
        }

    }

    private class MyFragmentPagerAdapter extends FragmentStateAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm, @NonNull Lifecycle lifecycle) {
            super(fm, lifecycle);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return PageFragmentProfile.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}