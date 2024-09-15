package vn.edu.usth.newsreader.duc_works;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.newsreader.R;

public class OpinionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opinion, container, false);

        Opinion1Fragment bao1Fragment = new Opinion1Fragment();
        Opinion2Fragment bao2Fragment = new Opinion2Fragment();
        Opinion3Fragment bao3Fragment = new Opinion3Fragment();

        // Thêm fragment Vietnamese working FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_opinion1, bao1Fragment)
                .commit();

        // Thêm fragment emal student vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_opinion2, bao2Fragment)
                .commit();

        // Thêm fragment bao yagi vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_opinion3, bao3Fragment)
                .commit();
        return view;
        //return inflater.inflate(R.layout.fragment_second, container, false);
    }
}