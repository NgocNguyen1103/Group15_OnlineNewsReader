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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sử dụng Executor để xử lý logic trong luồng nền (background thread)
        // Lấy ID của người dùng đang đăng nhập từ cơ sở dữ liệu
        // Lấy danh sách bài viết trong lịch sử dựa trên userId
        // Cập nhật giao diện trên Main Thread
        // Khởi tạo adapter với danh sách bài viết lịch sử
        // Gắn adapter vào RecyclerView
        // Trả về View của Fragment
        Executors.newSingleThreadExecutor().execute(() -> {
            int userId = AppDatabase.getInstance(requireContext()).userDao().getLoggedInUser().getId();
            List<Article> historyArticles = new HistoryManager(requireContext(), userId).getHistoryArticles();
            requireActivity().runOnUiThread(() -> {
                adapter = new NewsAdapter(requireContext(), historyArticles, userId);

                recyclerView.setAdapter(adapter);
            });
        });

        return view;
    }

}
