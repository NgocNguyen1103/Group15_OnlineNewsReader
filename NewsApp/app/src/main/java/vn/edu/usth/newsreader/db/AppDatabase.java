package vn.edu.usth.newsreader.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import vn.edu.usth.newsreader.login.User;
import vn.edu.usth.newsreader.news.Article;

// Annotation @Database khai báo lớp này là một cơ sở dữ liệu Room
// - entities: Danh sách các lớp Entity đại diện cho các bảng trong cơ sở dữ liệu (Article, User).
// - version: Phiên bản cơ sở dữ liệu, tăng lên 2 khi có thay đổi cấu trúc.
@Database(entities = {Article.class, User.class}, version = 2) // Tăng version lên 2
public abstract class AppDatabase extends RoomDatabase {
    // Biến tĩnh để lưu trữ một instance duy nhất của AppDatabase (Singleton pattern).
    // Phương thức trừu tượng trả về DAO để thao tác với bảng Article.
    // Phương thức trừu tượng trả về DAO để thao tác với bảng User.
    private static AppDatabase instance;
    public abstract ArticleDao articleDao();
    public abstract UserDao userDao();

    // Phương thức getInstance đảm bảo chỉ tạo một instance duy nhất của AppDatabase.
    // Sử dụng Room.databaseBuilder để khởi tạo cơ sở dữ liệu.
    // Tên cơ sở dữ liệu là "newsreader_db"
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "newsreader_db")
                    .fallbackToDestructiveMigration() // Xóa dữ liệu cũ và tạo lại cơ sở dữ liệu nếu có thay đổi cấu trúc
                    .build();
        }
        return instance;
    }
}

