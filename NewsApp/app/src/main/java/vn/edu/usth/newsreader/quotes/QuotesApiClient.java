package vn.edu.usth.newsreader.quotes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface QuotesApiClient {

    @GET("quotes")
    @Headers("X-Api-Key: 5Kweh91YJ9kBnv+jreCHfA==Mk6Vy5Zlfy0rkgpv")
    Call<List<Quote>> getQuotes(@Query("category") String category);

    class Factory {
        private static QuotesApiClient service;

        public static QuotesApiClient getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.api-ninjas.com/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(QuotesApiClient.class);
            }
            return service;
        }
    }
}
