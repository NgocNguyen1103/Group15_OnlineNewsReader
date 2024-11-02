package vn.edu.usth.newsreader.news;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import vn.edu.usth.newsreader.R;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<Article> articles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setOnRefreshListener(this::fetchNews);

        // Gọi hàm fetchNews() để tải dữ liệu ngay khi Fragment được tạo
        fetchNews();
    }

    private void fetchNews() {
        swipeRefreshLayout.setRefreshing(true);

        NewsApiService apiService = ApiClient.getClient().create(NewsApiService.class);
        Call<NewsResponse> call = apiService.getTopHeadlines("d2b1e0d9b6794535ab91504cd3b6dcb4", "us");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    articles = response.body().getArticles();
                    newsAdapter = new NewsAdapter(requireContext(), articles);
                    recyclerView.setAdapter(newsAdapter);
                } else {
                    Toast.makeText(requireContext(), "Failed to load news", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                if (isAdded()) {
                    Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
