package vn.edu.usth.newsreader.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.edu.usth.newsreader.news.Article;

@Dao
public interface ArticleDao {

    // Lấy lịch sử bài báo theo userId
    @Query("SELECT * FROM articles WHERE isHistory = 1 AND userId = :userId")
    List<Article> getHistoryArticles(int userId);

    // Chèn bài báo mới
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article article);

    // Cập nhật bài báo
    @Update
    void updateArticle(Article article);

    // Lấy bài báo dựa trên URL và userId
    @Query("SELECT * FROM articles WHERE url = :url AND userId = :userId")
    Article getArticleByUrl(String url, int userId);
}


