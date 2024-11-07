package vn.edu.usth.newsreader.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.edu.usth.newsreader.news.Article;

// Annotation @Dao (Data Access Object) cho biết đây là một giao diện để thao tác dữ liệu
// trong bảng của Room Database.
@Dao
public interface ArticleDao {

    // Lấy danh sách các bài báo đã được đánh dấu là "Lịch sử" (`isHistory = 1`)
    // và thuộc về một người dùng cụ thể (`userId`).
    // Room sẽ ánh xạ câu lệnh SQL bên trong @Query để trả về danh sách các đối tượng Article.
    @Query("SELECT * FROM articles WHERE isHistory = 1 AND userId = :userId")
    List<Article> getHistoryArticles(int userId);

    // Thêm một bài báo mới vào cơ sở dữ liệu.
    // Nếu bài báo đã tồn tại (xác định bởi khóa chính `id`), Room sẽ thay thế bản ghi cũ.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article article);

    // Cập nhật thông tin của một bài báo hiện có trong cơ sở dữ liệu.
    // Room sẽ xác định bài báo cần cập nhật dựa vào khóa chính `id`.
    @Update
    void updateArticle(Article article);

    // Tìm bài báo trong cơ sở dữ liệu dựa trên URL và userId.
    // Phương thức này giúp kiểm tra xem bài báo đã tồn tại trong cơ sở dữ liệu hay chưa.
    @Query("SELECT * FROM articles WHERE url = :url AND userId = :userId")
    Article getArticleByUrl(String url, int userId);
}


