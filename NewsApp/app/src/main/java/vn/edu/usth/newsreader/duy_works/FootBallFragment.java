package vn.edu.usth.newsreader.duy_works;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.newsreader.R;


public class FootBallFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_foot_ball, container, false);

        // Lắng nghe sự kiện khi người dùng nhấp vào fragment
        view.setOnClickListener(v -> {
            // Tạo Intent để chuyển sang BallDetailActivity
            Intent intent = new Intent(getActivity(), BallDetailActivity.class);

            startActivity(intent);
        });

        return view;
        //return inflater.inflate(R.layout.fragment_foot_ball, container, false);
    }
}