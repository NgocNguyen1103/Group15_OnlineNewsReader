package vn.edu.usth.newsreader.phuong_works;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.usth.newsreader.R;

public class Activity_podcast_2 extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_podcast2);


        ImageView button = findViewById(R.id.play_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer == null) {
                    playPodcast();
                    button.setImageResource(R.drawable.pause_button);
                }else {
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                        button.setImageResource(R.drawable.icon_play);
                    }else {
                        mediaPlayer.start();
                        button.setImageResource(R.drawable.pause_button);
                    }
                }
            }
        });
    }
    private void playPodcast(){
        mediaPlayer = MediaPlayer.create(Activity_podcast_2.this,R.raw.podcast_1);
        mediaPlayer.start();
    }
}