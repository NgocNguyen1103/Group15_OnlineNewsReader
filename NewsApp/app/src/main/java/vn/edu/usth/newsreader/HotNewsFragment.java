package vn.edu.usth.newsreader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotNewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HotNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotNewsFragment newInstance(String param1, String param2) {
        HotNewsFragment fragment = new HotNewsFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotnews, container, false);
        FootBallFragment footballNewsFragment = new FootBallFragment();
        ChessFragment chessNewsFragment = new ChessFragment();
        LolFragment lolNewsFragment = new LolFragment();

        // Thêm fragment bóng đá vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_football, footballNewsFragment)
                .commit();

        // Thêm fragment cờ vua vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_chess, chessNewsFragment)
                .commit();

        // Thêm fragment liên minh vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_lol, lolNewsFragment)
                .commit();
        //return inflater.inflate(R.layout.fragment_hotnews, container, false);
        return view;

    }
}