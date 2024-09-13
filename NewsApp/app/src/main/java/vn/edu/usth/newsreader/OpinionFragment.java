package vn.edu.usth.newsreader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpinionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpinionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpinionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpinionFragment newInstance(String param1, String param2) {
        OpinionFragment fragment = new OpinionFragment();
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
        View view = inflater.inflate(R.layout.fragment_opinion, container, false);

        Opinion1Fragment bao1Fragment = new Opinion1Fragment();
        Opinion2Fragment bao2Fragment = new Opinion2Fragment();
        Opinion3Fragment bao3Fragment = new Opinion3Fragment();

        // Thêm fragment Vietnamese working FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_bao1, bao1Fragment)
                .commit();

        // Thêm fragment emal student vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_bao2, bao2Fragment)
                .commit();

        // Thêm fragment bao yagi vào FragmentContainerView
        getChildFragmentManager().beginTransaction()
                .replace(R.id.id_bao3, bao3Fragment)
                .commit();
        return view;
        //return inflater.inflate(R.layout.fragment_second, container, false);
    }
}