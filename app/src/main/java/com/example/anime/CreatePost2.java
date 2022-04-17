package com.example.anime;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.activity.OnBackPressedCallback;

import com.example.anime.Constructors.SliderItemPost;
import com.smarteist.autoimageslider.SliderView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.List;

public class CreatePost2 extends Activity {

    private List<Uri> mArrayUri = new ArrayList<>();
    private RelativeLayout changeImages, deleteImages;
    private SliderView sliderView;
    private SliderAdapterForChosenImages adapter;
    private RelativeLayout add_image_image, nextStep;
    private ImageView backBlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post_2);
        getViews();
        getWindow().setStatusBarColor(getResources().getColor(R.color.background));


        backBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        add_image_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
            }
        });
        changeImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArrayUri.size() > 0) {
                    CropImage.activity(mArrayUri.get(pos))
                            .start(CreatePost2.this);
                }else{
                    Toast.makeText(CreatePost2.this, "First choose at least one picture!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mArrayUri.size()>0) {
                    mArrayUri.remove(pos);
                    int k = pos;
                    adapter.deleteItem(pos);
                    updateImages();
                    if (k == mArrayUri.size()) {
                        sliderView.setCurrentPagePosition(0);
                    } else {
                        sliderView.setCurrentPagePosition(k);
                    }
                    if (mArrayUri.size() == 0) {
                        add_image_image.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(CreatePost2.this, "First choose at least one picture!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("1", cropped.toString());
                Intent intent = new Intent(CreatePost2.this, PostItNow.class);
                intent.putExtra("size", mArrayUri.size());
                Log.d("00", String.valueOf(mArrayUri.size()));
                for(int i = 0; i < mArrayUri.size(); i ++){
                    intent.putExtra(String.valueOf(i), mArrayUri.get(i).toString());
                    Log.d(String.valueOf(i), mArrayUri.get(i).toString());
                }
                startActivity(intent);
            }
        });

    }
    private void getViews(){
        changeImages = findViewById(R.id.changeImages);
        deleteImages = findViewById(R.id.deleteImage);
        sliderView = findViewById(R.id.sliderOfChosen);
        add_image_image = findViewById(R.id.add_image_image);
        backBlog = findViewById(R.id.back_to_blog);
        nextStep = findViewById(R.id.nextStep);
    }
    private void setUpImages(){
        adapter = new SliderAdapterForChosenImages(this);
        add_image_image.setVisibility(View.INVISIBLE);
        addImagesToAdapter();
    }
    private void updateImages(){
        adapter.deleteAll();
        addImagesToAdapter();
    }
    int pos = 0;
    private void addImagesToAdapter(){
        for(int i = 0; i < mArrayUri.size(); i ++){
            SliderItemPost sliderItem = new SliderItemPost();
            sliderItem.setUri(mArrayUri.get(i));
            adapter.addItem(sliderItem);
        }
        sliderView.setSliderAdapter(adapter);
        sliderView.setCurrentPageListener(new SliderView.OnSliderPageListener() {
            @Override
            public void onSliderPageChanged(int position) {
                pos = position;
            }
        });
        if(mArrayUri.size() > 1){
            sliderView.setIndicatorEnabled(true);
        }else{
            sliderView.setIndicatorEnabled(false);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(imageurl);
                }
                // setting 1st selected image into image switcher
            } else {
                Uri imageurl = data.getData();
                mArrayUri.add(imageurl);
            }
            setUpImages();
        } else if(requestCode == 1){
            // show this if no image is selected
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                mArrayUri.set(pos, resultUri);
                int k = pos;
                adapter.changeItem(pos, resultUri);
                sliderView.setCurrentPagePosition(k);

                //updateImages();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
