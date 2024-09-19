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
        FragmentBao1 bao1Fragment = new FragmentBao1();
        FragmentBao2 bao2Fragment = new FragmentBao2();
        FragmentBao3 bao3Fragment = new FragmentBao3();

        // Thêm fragment social media vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_bao1, bao1Fragment)
                .commit();

        // Thêm fragment traffic vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_bao2, bao2Fragment)
                .commit();

        // Thêm fragment foreign language vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_bao3, bao3Fragment)
                .commit();
        return view;
    }

}