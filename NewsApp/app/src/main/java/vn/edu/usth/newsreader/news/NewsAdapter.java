package vn.edu.usth.newsreader.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.history.HistoryManager;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final Context context;
    private List<Article> articles;
    private final HistoryManager historyManager;

    // Constructor
    public NewsAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
        this.historyManager = new HistoryManager(context);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView source;
        ImageView imageView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsDescription);
            source = itemView.findViewById(R.id.newsSource);
            imageView = itemView.findViewById(R.id.newsImage);
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.source.setText(article.getSource().getName());

        // Tải hình ảnh từ URL sử dụng Glide
        if (article.getUrlToImage() != null) {
            Glide.with(context).load(article.getUrlToImage()).into(holder.imageView);
        } else {
            // Đặt hình ảnh mặc định hoặc để trống nếu urlToImage là null
            holder.imageView.setImageResource(0); // Hoặc set một giá trị mặc định khác
        }

        // Xử lý khi bấm vào item của RecyclerView
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("url", article.getUrl());
            historyManager.addToHistory(article);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
