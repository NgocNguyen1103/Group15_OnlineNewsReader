package vn.edu.usth.newsreader.phuong_works;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.edu.usth.newsreader.R;


public class Fragment_podcast_1 extends Fragment {


    public Fragment_podcast_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_podcast_1, container, false);
        ImageView podcastImage = view.findViewById(R.id.thumbnail);
        podcastImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_podcast_1.class);
                startActivity(intent);
            }
        });

        return view;
    }
}