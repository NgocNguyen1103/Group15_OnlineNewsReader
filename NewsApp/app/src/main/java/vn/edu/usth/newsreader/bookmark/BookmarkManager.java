package vn.edu.usth.newsreader.bookmark;

import java.util.List;
import java.util.concurrent.Executors;

import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.news.Article;

public class BookmarkManager {

    private final AppDatabase database;
    private final int userId;

    public BookmarkManager(AppDatabase database, int userId) {
        this.database = database;
        this.userId = userId;
    }

    // Lấy danh sách các bài viết được bookmark
    public List<Article> getBookmarkedArticles() {
        return database.articleDao().getBookmarkedArticles(userId);
    }

    // Thêm hoặc xóa bài viết khỏi bookmark
    public void toggleBookmark(Article article) {
        Executors.newSingleThreadExecutor().execute(() -> {
            Article existingArticle = database.articleDao().getArticleByUrl(article.getUrl(), userId);

            if (existingArticle == null) {
                article.setBookmarked(true);
                article.setUserId(userId);
                database.articleDao().insertArticle(article);
            } else {
                existingArticle.setBookmarked(!existingArticle.isBookmarked());
                database.articleDao().updateArticle(existingArticle);
            }
        });
    }
}
