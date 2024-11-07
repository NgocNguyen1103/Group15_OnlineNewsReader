package vn.edu.usth.newsreader.news;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.concurrent.Executors;

import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.bookmark.BookmarkManager;
import vn.edu.usth.newsreader.history.HistoryManager;
import vn.edu.usth.newsreader.db.AppDatabase;

// NewsAdapter là lớp Adapter để quản lý và hiển thị danh sách các bài báo (articles) trong RecyclerView
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final Context context; // Ngữ cảnh để làm việc với các thành phần của giao diện và gọi Intent
    private List<Article> articles; // Danh sách các bài báo sẽ được hiển thị trong RecyclerView
    private final HistoryManager historyManager; /** Quản lý lịch sử, dùng để lưu lại các bài báo đã xem*/
    public int userId;

    // Constructor
    public NewsAdapter(Context context, List<Article> articles, int userId) {
        this.context = context;
        this.articles = articles;
        this.userId = userId;
        this.historyManager = new HistoryManager(context, userId); // Khởi tạo đối tượng quản lý lịch sử
    }

    // Lớp con NewsViewHolder dùng để liên kết các phần tử giao diện của mỗi item trong danh sách bài báo
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView imageView;
        ImageButton bookmarkButton; // Thêm nút bookmark

        // Constructor của NewsViewHolder, liên kết các thành phần giao diện từ item_news.xml
        public NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsDescription);
            imageView = itemView.findViewById(R.id.newsImage);
            bookmarkButton = itemView.findViewById(R.id.bookmarkButton); // Gắn nút bookmark

        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Khởi tạo giao diện cho từng item bài báo từ file item_news.xml
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());

        String imageUrl = article.getUrlToImage();
        if (imageUrl != null) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.default_image);
        }

        // Cập nhật biểu tượng bookmark dựa trên trạng thái `isBookmarked`
        holder.bookmarkButton.setImageResource(
                article.isBookmarked() ? R.drawable.baseline_bookmark_1 : R.drawable.baseline_bookmark_0
        );


        // Sử dụng background thread để truy vấn UserDao
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            int userId = db.userDao().getLoggedInUser().getId(); // Truy vấn trong background thread

            // Xử lý bookmark
            holder.bookmarkButton.setOnClickListener(v -> {
                BookmarkManager bookmarkManager = new BookmarkManager(AppDatabase.getInstance(context), userId);

                Executors.newSingleThreadExecutor().execute(() -> {
                    // Thay đổi trạng thái bookmark trong cơ sở dữ liệu
                    bookmarkManager.toggleBookmark(article);

                    // Cập nhật trạng thái bài viết
                    article.setBookmarked(!article.isBookmarked());

                    // Cập nhật giao diện trên Main Thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        holder.bookmarkButton.setImageResource(
                                article.isBookmarked() ? R.drawable.baseline_bookmark_1 : R.drawable.baseline_bookmark_0
                        );
                    });
                });
            });
        });

        // Xử lý khi người dùng nhấn vào item bài báo
        holder.itemView.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                AppDatabase db = AppDatabase.getInstance(context);
                int userId = db.userDao().getLoggedInUser().getId();

                HistoryManager historyManager = new HistoryManager(context, userId);
                historyManager.addToHistory(article, userId);

                // Chuyển sang giao diện chi tiết trên main thread
                new android.os.Handler(Looper.getMainLooper()).post(() -> {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("url", article.getUrl());
                    context.startActivity(intent);
                });
            });
        });
    }




    @Override
    public int getItemCount() {
//         Trả về tổng số bài báo trong danh sách
        return articles.size();
    }
}
