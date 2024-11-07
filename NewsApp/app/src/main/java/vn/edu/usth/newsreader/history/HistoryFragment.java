package vn.edu.usth.newsreader.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.news.Article;
import vn.edu.usth.newsreader.news.NewsAdapter;

import java.util.List;
import java.util.concurrent.Executors;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private HistoryManager historyManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo lịch sử khi có userId
        Executors.newSingleThreadExecutor().execute(() -> {
            int userId = AppDatabase.getInstance(requireContext()).userDao().getLoggedInUser().getId();

            List<Article> historyArticles = new HistoryManager(requireContext(), userId).getHistoryArticles();

            // Cập nhật giao diện trên main thread
            requireActivity().runOnUiThread(() -> {
                adapter = new NewsAdapter(requireContext(), historyArticles);
                recyclerView.setAdapter(adapter);
            });
        });

        return view;
    }

}
