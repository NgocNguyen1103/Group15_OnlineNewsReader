package vn.edu.usth.newsreader.duc_works;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.newsreader.R;


public class PerspectiveFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perspective, container, false);
<<<<<<< HEAD
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_perspective, container, false);
        // Tạo 3 fragment cho các bài báo
        FragmentBao1 bao1Fragment = new FragmentBao1();
        FragmentBao2 bao2Fragment = new FragmentBao2();
        FragmentBao3 bao3Fragment = new FragmentBao3();
=======

        Perspective1Fragment bao1Fragment = new Perspective1Fragment();
        Perspective2Fragment bao2Fragment = new Perspective2Fragment();
        Perspective3Fragment bao3Fragment = new Perspective3Fragment();
>>>>>>> main

        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_perspective1, bao1Fragment)
                .commit();

        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_perspective2, bao2Fragment)
                .commit();

        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_perspective3, bao3Fragment)
                .commit();
        return view;
    }

}