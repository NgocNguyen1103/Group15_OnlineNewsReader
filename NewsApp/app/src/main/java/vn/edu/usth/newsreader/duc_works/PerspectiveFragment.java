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
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_perspective, container, false);

        // Tạo 3 fragment cho các bài báo
        Perspective1Fragment bao1Fragment = new Perspective1Fragment();
        Perspective2Fragment bao2Fragment = new Perspective2Fragment();
        Perspective3Fragment bao3Fragment = new Perspective3Fragment();

        // Thêm fragment social media vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_perspective1, bao1Fragment)
                .commit();

        // Thêm fragment traffic vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_perspective2, bao2Fragment)
                .commit();

        // Thêm fragment foreign language vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_perspective3, bao3Fragment)
                .commit();
        return view;
    }

}