package vn.edu.usth.newsreader.nghia_works;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import vn.edu.usth.newsreader.R;

public class Frag_ext extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_nghiafrag4, container, false);
        View view = inflater.inflate(R.layout.fragment_frag_ext, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_ext.class);
                startActivity(intent);
            }
        });
        return view;
    }
}