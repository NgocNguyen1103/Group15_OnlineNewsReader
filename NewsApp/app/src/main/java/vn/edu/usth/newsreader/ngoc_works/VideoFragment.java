package vn.edu.usth.newsreader.ngoc_works;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.newsreader.R;


public class VideoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_video, container, false);
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        // Load Video1Fragment
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.video1_fragment, new Video1Fragment());
        transaction.replace(R.id.video2_fragment, new Video2Fragment());
        transaction.replace(R.id.video3_fragment, new Video3Fragment());

        transaction.commit();

        return view;
    }
}