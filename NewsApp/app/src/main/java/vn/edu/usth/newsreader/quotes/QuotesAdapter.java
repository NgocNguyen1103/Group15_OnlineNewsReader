package vn.edu.usth.newsreader.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.usth.newsreader.R;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder> {

    private List<Quote> quotes;

    public QuotesAdapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.quoteTextView.setText(quote.getQuote());
        holder.authorTextView.setText("- " + quote.getAuthor());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public static class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView quoteTextView;
        TextView authorTextView;

        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);
            quoteTextView = itemView.findViewById(R.id.quoteTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
        }
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }
}
