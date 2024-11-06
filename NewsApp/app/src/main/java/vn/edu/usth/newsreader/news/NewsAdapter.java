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

// NewsAdapter là lớp Adapter để quản lý và hiển thị danh sách các bài báo (articles) trong RecyclerView
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final Context context; // Ngữ cảnh để làm việc với các thành phần của giao diện và gọi Intent
    private List<Article> articles; // Danh sách các bài báo sẽ được hiển thị trong RecyclerView
    private final HistoryManager historyManager; /** Quản lý lịch sử, dùng để lưu lại các bài báo đã xem*/

        // Constructor
    public NewsAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
        this.historyManager = new HistoryManager(context); // Khởi tạo đối tượng quản lý lịch sử
    }

    // Lớp con NewsViewHolder dùng để liên kết các phần tử giao diện của mỗi item trong danh sách bài báo
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView source;
        ImageView imageView;

        // Constructor của NewsViewHolder, liên kết các thành phần giao diện từ item_news.xml
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
        // Khởi tạo giao diện cho từng item bài báo từ file item_news.xml
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // Lấy bài báo tại vị trí hiện tại trong danh sách và cập nhật giao diện
        Article article = articles.get(position);
        // Đặt tiêu đề bài báo, mô tả bài báo, tên nguồn của bài báo
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.source.setText(article.getSource().getName());

        String imageUrl = article.getUrlToImage();
        if (imageUrl != null) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image) // Hiển thị trong khi tải
                    .error(R.drawable.error_image) // Hiển thị nếu có lỗi
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.default_image); // Đặt ảnh mặc định nếu URL là null
        }


        // Xử lý khi người dùng nhấn vào item bài báo
        holder.itemView.setOnClickListener(v -> {
            // Tạo Intent để mở màn hình chi tiết bài báo
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("url", article.getUrl()); // Truyền URL bài báo cho DetailActivity
            historyManager.addToHistory(article);     // Thêm bài báo vào lịch sử xem

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
//         Trả về tổng số bài báo trong danh sách
        return articles.size();
    }
}
