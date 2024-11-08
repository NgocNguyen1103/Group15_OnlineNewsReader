package vn.edu.usth.newsreader.news;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.login.User;

public class NewsFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout; // Để người dùng làm mới danh sách tin tức
    private RecyclerView recyclerView;             // RecyclerView hiển thị danh sách các bài báo
    private NewsAdapter newsAdapter;               // Adapter quản lý và hiển thị bài báo
    private List<Article> articles = new ArrayList<>(); // Danh sách các bài báo từ API

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment_home cho Fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lấy userId trong background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase database = AppDatabase.getInstance(requireContext());
            User loggedInUser = database.userDao().getLoggedInUser(); // Lấy người dùng đang đăng nhập

            if (loggedInUser != null) {
                int userId = loggedInUser.getId(); // Lấy userId từ cơ sở dữ liệu

                // Chuyển về Main Thread để khởi tạo adapter và gắn vào RecyclerView
                new Handler(Looper.getMainLooper()).post(() -> {
                    newsAdapter = new NewsAdapter(requireContext(), articles, userId);
                    recyclerView.setAdapter(newsAdapter);
                });
            } else {
                // Chuyển về Main Thread để hiển thị thông báo lỗi
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(requireContext(), "No user logged in. Please log in first.", Toast.LENGTH_SHORT).show();
                });
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this::fetchNews);

        fetchNews(); // Gọi hàm fetchNews() để tải dữ liệu ngay khi Fragment được tạo
    }

    private void fetchNews() {
        swipeRefreshLayout.setRefreshing(true); // Hiển thị biểu tượng làm mới

        NewsApiService apiService = NewsApiClient.getInstance().create(NewsApiService.class);
        Call<NewsResponse> call = apiService.getTopHeadlines("techcrunch", "d2b1e0d9b6794535ab91504cd3b6dcb4");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getArticles() != null) {

                    articles.clear(); // Xóa dữ liệu cũ
                    articles.addAll(response.body().getArticles()); // Thêm dữ liệu mới

                    // Lấy userId và cập nhật trạng thái bookmark từ cơ sở dữ liệu
                    Executors.newSingleThreadExecutor().execute(() -> {
                        AppDatabase database = AppDatabase.getInstance(requireContext());
                        User loggedInUser = database.userDao().getLoggedInUser();

                        if (loggedInUser != null) {
                            int userId = loggedInUser.getId();
                            for (Article article : articles) {
                                Article dbArticle = database.articleDao().getArticleByUrl(article.getUrl(), userId);
                                if (dbArticle != null) {
                                    article.setBookmarked(dbArticle.isBookmarked());
                                }
                            }
                        }

                        // Cập nhật giao diện trên Main Thread
                        new Handler(Looper.getMainLooper()).post(() -> {
                            newsAdapter.notifyDataSetChanged(); // Cập nhật UI
                        });
                    });
                } else {
                    Toast.makeText(requireContext(), "Failed to load news", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false); // Tắt biểu tượng làm mới
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                if (isAdded()) {
                    Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false); // Tắt biểu tượng làm mới
            }
        });
    }

}
