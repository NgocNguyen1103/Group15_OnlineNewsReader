package vn.edu.usth.newsreader.duy_works;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.newsreader.R;


public class LolFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lol, container, false);
        view.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), LolDetailActivity.class);

            startActivity(intent);
        });

        return view;

    }
}