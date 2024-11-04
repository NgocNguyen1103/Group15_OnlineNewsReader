package vn.edu.usth.newsreader.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// NewsApiService là một interface định nghĩa các phương thức để tương tác với API của NewsAPI.
public interface NewsApiService {

    /**
     * Phương thức này định nghĩa một yêu cầu HTTP GET để lấy các tin tức hàng đầu (top headlines).
     * Endpoint của API là "top-headlines".
     *
     * @param apiKey API key của bạn, dùng để xác thực khi thực hiện yêu cầu đến server.
     * @param country Mã quốc gia (ví dụ: "us" cho Hoa Kỳ) để giới hạn các tin tức theo quốc gia cụ thể.
     * @return Trả về một đối tượng `Call<NewsResponse>` dùng để thực hiện yêu cầu và nhận phản hồi từ API.
     *         `NewsResponse` là kiểu dữ liệu sẽ nhận từ phản hồi của API, chứa thông tin các bài báo.
     */
    @GET("top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("apiKey") String apiKey,  // Tham số API key được truyền vào trong URL của yêu cầu.
            @Query("country") String country // Tham số country để chỉ định quốc gia lấy tin tức.
    );
}


