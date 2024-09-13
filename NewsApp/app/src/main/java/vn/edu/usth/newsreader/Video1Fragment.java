package vn.edu.usth.newsreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Video1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Video1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Video1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Video1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Video1Fragment newInstance(String param1, String param2) {
        Video1Fragment fragment = new Video1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video1, container, false);
//        VideoView videoView = view.findViewById(R.id.video_view);
//        String path ="android.resource://" + getActivity().getPackageName() + "/raw/android_11";
//        Uri videoUri = Uri.parse(path);
//        videoView.setVideoURI(videoUri);
//        videoView.start();
//
        view.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), vid1_detailActivity.class);
            startActivity(intent);
        });
        return view;

//        return inflater.inflate(R.layout.fragment_video1, container, false);
    }
}