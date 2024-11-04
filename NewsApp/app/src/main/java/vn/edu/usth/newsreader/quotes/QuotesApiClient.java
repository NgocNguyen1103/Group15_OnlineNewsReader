package vn.edu.usth.newsreader.quotes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

// QuotesApiClient là một interface sử dụng Retrofit để tương tác với API lấy danh sách câu trích dẫn.
public interface QuotesApiClient {

    // Phương thức getQuotes được dùng để lấy danh sách các câu trích dẫn dựa trên category chỉ định
    // @GET chỉ định endpoint "/quotes", và @Headers thêm thông tin API key cần thiết cho mỗi request
    @GET("quotes")
    @Headers("X-Api-Key: 5Kweh91YJ9kBnv+jreCHfA==Mk6Vy5Zlfy0rkgpv")
    Call<List<Quote>> getQuotes(@Query("category") String category);

    // Factory là một lớp tĩnh bên trong QuotesApiClient dùng để quản lý và khởi tạo một instance của QuotesApiClient
    class Factory {
        private static QuotesApiClient service;

        // Phương thức getInstance sẽ trả về instance của QuotesApiClient.
        // Nếu instance chưa được khởi tạo, Retrofit sẽ được cấu hình với base URL và converter cần thiết,
        // sau đó tạo instance của QuotesApiClient để thực hiện các yêu cầu API.
        public static QuotesApiClient getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.api-ninjas.com/v1/") // Địa chỉ cơ bản của API
                        .addConverterFactory(GsonConverterFactory.create()) // Sử dụng GsonConverterFactory để parse JSON
                        .build();
                service = retrofit.create(QuotesApiClient.class); // Tạo instance của QuotesApiClient từ Retrofit
            }
            return service;
        }
    }
}

