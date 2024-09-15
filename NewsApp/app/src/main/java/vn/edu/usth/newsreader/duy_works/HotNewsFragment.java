package vn.edu.usth.newsreader.duy_works;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.newsreader.R;


public class HotNewsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotnews, container, false);

        FootBallFragment footballNewsFragment = new FootBallFragment();
        ChessFragment chessNewsFragment = new ChessFragment();
        LolFragment lolNewsFragment = new LolFragment();


        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_football, footballNewsFragment)
                .commit();


        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_chess, chessNewsFragment)
                .commit();


        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_lol, lolNewsFragment)
                .commit();
        return view;

    }
}