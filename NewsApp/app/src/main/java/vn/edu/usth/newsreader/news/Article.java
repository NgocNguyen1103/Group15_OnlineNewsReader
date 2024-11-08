package vn.edu.usth.newsreader.news;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Annotation đánh dấu lớp này là một thực thể (Entity) cho Room Database.
// Bảng trong cơ sở dữ liệu SQLite sẽ được đặt tên là "articles".
@Entity(tableName = "articles")
public class Article {

    // Trường này là khóa chính (Primary Key) cho bảng "articles".
    // `autoGenerate = true` đảm bảo rằng Room sẽ tự động tăng giá trị ID mỗi khi một bản ghi mới được thêm vào bảng.
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private boolean isHistory;    // Trường thêm để quản lý lịch sử
    private int userId;     // Trường thêm để liên kết với người dùng
    private boolean isBookmarked; // Dùng cho Bookmark


    /*
    Room sẽ ánh xạ(mapping) lớp này thành bảng SQL như sau
    CREATE TABLE articles (
            id INTEGER PRIMARY KEY AUTOINCREMENT, -- Khóa chính, tự động tăng
            title TEXT,                          -- Tiêu đề bài báo
                    description TEXT,                    -- Mô tả bài báo
                    url TEXT,                            -- URL bài báo
                    isHistory INTEGER,                   -- Cột lưu trạng thái "Lịch sử"
                    userId INTEGER                       -- ID người dùng liên kết bài báo
    );
*/

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Getters và Setters
    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmark) {
        isBookmarked = bookmark;
    }
}
