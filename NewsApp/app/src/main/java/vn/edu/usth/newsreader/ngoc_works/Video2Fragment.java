package vn.edu.usth.newsreader.ngoc_works;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.newsreader.R;


public class Video2Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_video2, container, false);
        View view = inflater.inflate(R.layout.fragment_video2, container, false);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), vid2_detailActivity.class);
            startActivity(intent);
        });
        return view;
    }
}