package vn.edu.usth.newsreader.quotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.newsreader.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuotesFragment extends Fragment {

    private RecyclerView quotesRecyclerView;
    private QuotesAdapter quotesAdapter;
    private List<Quote> allQuotes = new ArrayList<>();

    private final List<String> categories = Arrays.asList(
            "age", "alone", "amazing", "anger", "architecture", "art",
            "attitude", "beauty", "best", "birthday", "business",
            "car", "change", "communication", "computers", "cool",
            "courage", "dad", "dating"
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        quotesRecyclerView = view.findViewById(R.id.recyclerView);
        quotesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        quotesAdapter = new QuotesAdapter(new ArrayList<>());
        quotesRecyclerView.setAdapter(quotesAdapter);

        fetchQuotesFromCategories();

        return view;
    }

    private void fetchQuotesFromCategories() {
        String apiKey = "5Kweh91YJ9kBnv+jreCHfA==Mk6Vy5Zlfy0rkgpv";

        for (String category : categories) {
            QuotesApiClient.getService().getQuotes(apiKey, category).enqueue(new Callback<List<Quote>>() {
                @Override
                public void onResponse(@NonNull Call<List<Quote>> call, @NonNull Response<List<Quote>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        allQuotes.addAll(response.body());
                        quotesAdapter.setQuotes(allQuotes);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Quote>> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(), "Error loading quotes: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
