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


import java.util.List;

import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.news.Article;
import vn.edu.usth.newsreader.news.NewsAdapter;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private HistoryManager historyManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        historyManager = new HistoryManager(getContext());

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lấy danh sách bài báo từ lịch sử
        List<Article> history = historyManager.getHistory();

        // Hiển thị lịch sử nếu có bài báo
        newsAdapter = new NewsAdapter(getContext(), history);
        recyclerView.setAdapter(newsAdapter);
    }
}
