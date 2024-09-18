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

        Perspective1Fragment bao1Fragment = new Perspective1Fragment();
        Perspective2Fragment bao2Fragment = new Perspective2Fragment();
        Perspective3Fragment bao3Fragment = new Perspective3Fragment();

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