package vn.edu.usth.newsreader.ngoc_works;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.newsreader.R;

public class vid1_detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vid1_detail);

        VideoView videoView = findViewById(R.id.video_view);
        String path ="android.resource://" + getPackageName() + "/raw/hanoi_flood";
        Uri videoUri = Uri.parse(path);
        videoView.setVideoURI(videoUri);
        //videoView.start();
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

    }


}