package vn.edu.usth.newsreader.nghia_works;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import vn.edu.usth.newsreader.R;


public class Frag_setting extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_nghiafrag2, container, false);
        View view = inflater.inflate(R.layout.fragment_frag_setting, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_setting.class);
                startActivity(intent);
            }
        });
        return view;
    }
}