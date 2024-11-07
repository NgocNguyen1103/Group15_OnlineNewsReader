package vn.edu.usth.newsreader.history;

import android.content.Context;

import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.news.Article;
import java.util.List;
import java.util.concurrent.Executors;

public class HistoryManager {

    // Biến database để thao tác với cơ sở dữ liệu thông qua AppDatabase
    // ID của người dùng hiện tại, dùng để lọc lịch sử theo người dùng
    private final AppDatabase database;
    private final int userId;

    // Constructor để khởi tạo HistoryManager
    // - context: Dùng để lấy instance của AppDatabase.
    // - userId: ID của người dùng, giúp quản lý lịch sử riêng của từng người.
    public HistoryManager(Context context, int userId) {
        this.database = AppDatabase.getInstance(context);
        this.userId = userId;
    }

    // Phương thức thêm bài viết vào lịch sử
    // - article: Đối tượng bài viết cần thêm.
    // - userId: ID người dùng để gán bài viết vào đúng lịch sử của họ
    public void addToHistory(Article article, int userId) {

        // Sử dụng một Executor để chạy tác vụ trong luồng riêng, tránh chặn giao diện chính (Main Thread).
        Executors.newSingleThreadExecutor().execute(() -> {

            // Kiểm tra xem bài viết đã tồn tại trong lịch sử của người dùng chưa
            // Nếu bài viết chưa tồn tại, đánh dấu là lịch sử và gán ID người dùng
            // Thêm bài viết mới vào cơ sở dữ liệu
            // Nếu bài viết đã tồn tại, cập nhật trạng thái lịch sử
            // Cập nhật bài viết trong cơ sở dữ liệu

            Article existingArticle = database.articleDao().getArticleByUrl(article.getUrl(), userId);
            if (existingArticle == null) {
                article.setHistory(true);
                article.setUserId(userId);
                database.articleDao().insertArticle(article);
            } else {
                existingArticle.setHistory(true);
                database.articleDao().updateArticle(existingArticle);
            }
        });
    }

    // Phương thức lấy danh sách các bài viết trong lịch sử
    // Trả về danh sách các bài viết đã được lưu vào lịch sử của người dùng hiện tại
    public List<Article> getHistoryArticles() {
        return database.articleDao().getHistoryArticles(userId);
    }
}

