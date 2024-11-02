package vn.edu.usth.newsreader.news;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;

    // Hàm này trả về một instance của Retrofit. Nó sẽ kiểm tra xem Retrofit đã được khởi tạo chưa
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

