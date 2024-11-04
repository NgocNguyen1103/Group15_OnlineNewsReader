package vn.edu.usth.newsreader.quotes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// QuotesApiClient tạo và cung cấp một instance của QuotesApiService.
public class QuotesApiClient {
    private static QuotesApiService service;

    // Phương thức getService trả về instance của QuotesApiService.
    public static QuotesApiService getService() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.api-ninjas.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(QuotesApiService.class);
        }
        return service;
    }
}
