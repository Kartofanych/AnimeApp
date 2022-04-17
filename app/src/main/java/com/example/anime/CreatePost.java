package com.example.anime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ortiz.touchview.TouchImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePost extends AppCompatActivity {


    int PICK_IMAGE_MULTIPLE = 1;
    private List<String> imagesEncodedList;
    private Map<String, String> mArrayUri;
    private GalleryAdapter galleryAdapter;
    private RecyclerView recyclerView;
    private List<String> images = new ArrayList<>();
    private TouchImageView previewImg;
    private ConstraintLayout forI;
    private ImageView rotateR, rotateL, next;
    private List<Bitmap> cropped = new ArrayList<>();

    private LinearLayout linHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        getViews();
        loadImages();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("1", cropped.toString());
                Intent intent = new Intent(CreatePost.this, PostItNow.class);
                intent.putExtra("size", mArrayUri.size());
                int i  = 0;
                for(String key : mArrayUri.keySet()){
                    intent.putExtra(String.valueOf(i), key);
                    i++;
                }
                startActivity(intent);
            }
        });

    }

    private void getViews(){
       // recyclerView = findViewById(R.id.recyclerPhotos);
        previewImg = findViewById(R.id.img_preview);
        next = findViewById(R.id.next);
        forI = findViewById(R.id.forI);
        mArrayUri = new HashMap<>();
    }

    private Map<String, Integer> clicks = new HashMap<>();
    private String previousPath = "haha";
    private View previousView;

    private Map<String, View> previousViews = new HashMap<>();
    private int mode = 1;

    private Map<Integer, TextView> num_list = new HashMap<>();

    private Map<String, Integer> num = new HashMap<>();
    private int kol = 0;

    private void loadImages(){
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
       /* galleryAdapter = new GalleryAdapter(this, images, new GalleryAdapter.PhotoListener() {
            @Override
            public void onPhotoClick(String path, View v, List<String> images) {

                if(mode == 1) {
                    if (!previousPath.equals(path)) {
                        File imgFile = new File(path);
                        if (imgFile.exists()) {
                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            previewImg.setImageBitmap(myBitmap);
                        }
                        v.findViewById(R.id.smoke).setVisibility(View.VISIBLE);
                        if(previousView!=null){
                            mArrayUri.remove(previousPath);
                            previousView.findViewById(R.id.smoke).setVisibility(View.GONE);
                        }
                        previousView = v;
                        mArrayUri.put(path, path);
                        previousPath = path;

                    } else{
                        previewImg.setImageResource(R.drawable.marin);
                        v.findViewById(R.id.smoke).setVisibility(View.VISIBLE);
                        Log.d("hey", "hey");
                        mArrayUri.remove(path);
                        previousPath = "haha";
                        previousView = null;
                    }
                }else if(mode == 2){
                    if(clicks.get(path) == null || clicks.get(path) == 0){
                        if(previousView!=null){
                            mArrayUri.remove(previousPath);
                            previousView.findViewById(R.id.smoke).setVisibility(View.INVISIBLE);
                            previousView = null;
                        }
                        v.findViewById(R.id.smoke).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.num_rel).setVisibility(View.VISIBLE);

                        num_list.put(num_list.size()+kol, v.findViewById(R.id.text_num));

                        mArrayUri.put(path, path);
                        File imgFile = new File(path);
                        if (imgFile.exists()) {
                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            previewImg.setImageBitmap(myBitmap);
                        }
                        clicks.put(path, 1);
                        previousViews.put(path, v);
                        mArrayUri.put(path, path);
                    }else if(clicks.get(path) == 1){
                        v.findViewById(R.id.smoke).setVisibility(View.INVISIBLE);
                        TextView t = v.findViewById(R.id.text_num);
                        if(Integer.parseInt(t.getText().toString()) < num_list.size()){
                            kol++;
                        }
                        num_list.remove(Integer.parseInt(t.getText().toString()));
                        v.findViewById(R.id.num_rel).setVisibility(View.INVISIBLE);

                        mArrayUri.remove(path);
                        clicks.put(path, 0);
                        if(mArrayUri.size() == 0){
                            mode = 1;
                            kol = 0;
                        }
                    }
                }
                for(String key : mArrayUri.keySet()){
                    Log.d("key", key);

                    //do something to value
                }

            }

            @Override
            public void onPhotoLongClick(String path, View v, List<String> images) {
                if(mode != 2) {
                    mode = 2;
                }

                //onPhotoClick(path, v);
            }
        });
        recyclerView.setAdapter(galleryAdapter);*/


    }


}