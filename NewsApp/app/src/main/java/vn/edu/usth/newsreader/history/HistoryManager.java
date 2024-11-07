package vn.edu.usth.newsreader.history;

import android.content.Context;

import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.news.Article;
import java.util.List;
import java.util.concurrent.Executors;

public class HistoryManager {

    private final AppDatabase database;
    private final int userId;

    public HistoryManager(Context context, int userId) {
        this.database = AppDatabase.getInstance(context);
        this.userId = userId;
    }

    public void addToHistory(Article article, int userId) {
        Executors.newSingleThreadExecutor().execute(() -> {
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




    public List<Article> getHistoryArticles() {
        return database.articleDao().getHistoryArticles(userId);
    }
}

