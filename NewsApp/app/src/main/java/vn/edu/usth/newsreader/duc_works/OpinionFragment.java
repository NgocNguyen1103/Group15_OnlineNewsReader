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
        View view = inflater.inflate(R.layout.fragment_opinion, container, false);

        Opinion1Fragment news1Fragment = new Opinion1Fragment();
        Opinion2Fragment news2Fragment = new Opinion2Fragment();
        Opinion3Fragment news3Fragment = new Opinion3Fragment();

        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_opinion1, news1Fragment)
                .commit();

        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_opinion2, news2Fragment)
                .commit();

        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_opinion3, news3Fragment)
                .commit();
        return view;
        //return inflater.inflate(R.layout.fragment_second, container, false);
    }
}