package com.example.anime.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.anime.R;

public class ForumFragment extends Fragment {

    //private NiceVideoPlayer mNiceVideoPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_forum, container, false);
        //init(root);


        return root;
    }
   /* private void init(View r) {
        mNiceVideoPlayer = (NiceVideoPlayer) r.findViewById(R.id.nice_video_player);
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE); // IjkPlayer or MediaPlayer
        TxVideoPlayerController controller = new TxVideoPlayerController(getContext());
        controller.setTitle("mTitle");
        controller.setClarity(getClarites(), 0);    // 设置清晰度以及默认播放的清晰度
        Glide.with(this)
                .load(getResources().getDrawable(R.drawable.marin))
                .placeholder(R.drawable.img_default)
                .into(controller.imageView());
        mNiceVideoPlayer.setController(controller);
    }

    private List<Clarity> getClarites() {
        List<Clarity> clarities = new ArrayList<>();
        clarities.add(new Clarity("270p", "270P","https://aniblock.ru/files2/_720_2147420592.mp4"));
        clarities.add(new Clarity("480p", "480P","https://aniblock.ru/files2/_720_2147420592.mp4"));
        clarities.add(new Clarity("720p", "720P","https://aniblock.ru/files2/_720_2147420592.mp4"));
        clarities.add(new Clarity("1080p", "1080P","https://aniblock.ru/files2/_720_2147420592.mp4"));
        return clarities;
    }*/
    @Override
    public void onStop() {
        super.onStop();
       // NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }


}