package vn.edu.usth.newsreader.quotes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

// QuotesApiService là một interface định nghĩa các phương thức để tương tác với API lấy danh sách câu trích dẫn.
public interface QuotesApiService {

    // Phương thức getQuotes thực hiện một yêu cầu GET đến endpoint "/quotes".
    // Yêu cầu này nhận cả apiKey và category dưới dạng tham số.
    @GET("quotes")
    Call<List<Quote>> getQuotes(
            @Header("X-Api-Key") String apiKey,    // API key để xác thực với server
            @Query("category") String category     // Danh mục câu trích dẫn để lọc kết quả
    );
}
