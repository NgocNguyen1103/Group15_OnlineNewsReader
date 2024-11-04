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

    private SwipeRefreshLayout swipeRefreshLayout; // Cho phép người dùng kéo xuống để làm mới danh sách tin tức
    private RecyclerView recyclerView;             // RecyclerView hiển thị danh sách các bài báo
    private NewsAdapter newsAdapter;               // Adapter để quản lý và hiển thị bài báo
    private List<Article> articles = new ArrayList<>(); // Danh sách các bài báo nhận từ API

    // Phương thức này khởi tạo giao diện cho Fragment, trả về layout fragment_home
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // Được gọi ngay sau onCreateView(), thiết lập các thành phần giao diện và gọi API để tải dữ liệu
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Liên kết SwipeRefreshLayout và RecyclerView từ layout fragment_home.xml
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Đặt LayoutManager cho RecyclerView

        // Thiết lập sự kiện khi người dùng kéo xuống để làm mới danh sách
        swipeRefreshLayout.setOnRefreshListener(this::fetchNews);

        // Gọi hàm fetchNews() để tải dữ liệu ngay khi Fragment được tạo
        fetchNews();
    }

    // Phương thức fetchNews() gửi yêu cầu lấy dữ liệu từ API
    private void fetchNews() {
        swipeRefreshLayout.setRefreshing(true); // Hiển thị biểu tượng làm mới

        // Khởi tạo API service từ ApiClient để gọi phương thức lấy tin tức
        NewsApiService apiService = ApiClient.getClient().create(NewsApiService.class);
        Call<NewsResponse> call = apiService.getTopHeadlines("d2b1e0d9b6794535ab91504cd3b6dcb4", "us"); // Tạo yêu cầu gọi API với API Key và quốc gia "us"

        // Thực hiện yêu cầu bất đồng bộ với enqueue để lấy dữ liệu từ API
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                // Kiểm tra nếu API trả về thành công và có dữ liệu
                if (response.isSuccessful() && response.body() != null) {
                    // Lấy danh sách bài báo từ phản hồi API và khởi tạo adapter với danh sách này
                    articles = response.body().getArticles();
                    newsAdapter = new NewsAdapter(requireContext(), articles);
                    recyclerView.setAdapter(newsAdapter); // Đặt adapter cho RecyclerView
                } else {
                    // Nếu API trả về không thành công, hiển thị thông báo lỗi
                    Toast.makeText(requireContext(), "Failed to load news", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false); // Ẩn biểu tượng làm mới
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                // Khi có lỗi xảy ra, kiểm tra Fragment đã được thêm vào Activity chưa để đảm bảo gọi Toast an toàn
                if (isAdded()) {
                    // Hiển thị thông báo lỗi với thông điệp lỗi từ Throwable
                    Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false); // Ẩn biểu tượng làm mới
            }
        });
    }
}

