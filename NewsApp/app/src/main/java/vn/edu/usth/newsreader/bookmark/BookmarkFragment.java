package vn.edu.usth.newsreader.bookmark;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executors;

import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.news.Article;
import vn.edu.usth.newsreader.news.NewsAdapter;

public class BookmarkFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lấy dữ liệu từ cơ sở dữ liệu trong background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            // Lấy userId từ UserDao
            AppDatabase database = AppDatabase.getInstance(requireContext());
            int userId = database.userDao().getLoggedInUser().getId();

            // Lấy danh sách bài viết được bookmark
            List<Article> bookmarkedArticles = database.articleDao().getBookmarkedArticles(userId);

            // Cập nhật giao diện trên Main Thread
            new Handler(Looper.getMainLooper()).post(() -> {
                adapter = new NewsAdapter(requireContext(), bookmarkedArticles, userId);
                recyclerView.setAdapter(adapter);
            });
        });

        return view;
    }
}
