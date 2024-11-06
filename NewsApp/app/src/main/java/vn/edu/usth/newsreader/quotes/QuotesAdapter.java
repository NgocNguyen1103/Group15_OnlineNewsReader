package vn.edu.usth.newsreader.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.usth.newsreader.R;

import java.util.List;

// Lớp QuotesAdapter là một adapter cho RecyclerView, dùng để hiển thị danh sách các câu trích dẫn
public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder> {

    // Danh sách các câu trích dẫn sẽ hiển thị
    private List<Quote> quotes;

    // Constructor để khởi tạo adapter với danh sách các câu trích dẫn
    public QuotesAdapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    // Tạo ViewHolder và gán layout item_quote cho từng mục trong danh sách
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    // Gán dữ liệu từ đối tượng Quote vào các View trong ViewHolder
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.quoteTextView.setText(quote.getQuote()); // Gán nội dung câu trích dẫn vào TextView
        holder.authorTextView.setText("- " + quote.getAuthor()); // Gán tên tác giả vào TextView
    }

    @Override
    // Trả về số lượng mục trong danh sách quotes
    public int getItemCount() {
        return quotes.size();
    }

    // Lớp ViewHolder dùng để giữ các View của một mục trong danh sách, giúp tái sử dụng View hiệu quả
    public static class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView quoteTextView;
        TextView authorTextView;

        // Khởi tạo ViewHolder và ánh xạ các View trong item_quote.xml
        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);
            quoteTextView = itemView.findViewById(R.id.quoteTextView); // TextView hiển thị nội dung câu trích dẫn
            authorTextView = itemView.findViewById(R.id.authorTextView); // TextView hiển thị tên tác giả
        }
    }

    // Phương thức này cập nhật danh sách quotes và thông báo RecyclerView làm mới dữ liệu
    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }
}

