package vn.edu.usth.newsreader.quotes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import java.util.List;

public interface QuotesApiService {
    @GET("quotes")
    Call<List<Quote>> getQuotes(
            @Header("X-Api-Key") String apiKey
    );
}

