package vn.edu.usth.newsreader.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import vn.edu.usth.newsreader.login.User;
import vn.edu.usth.newsreader.news.Article;

@Database(entities = {Article.class, User.class}, version = 2) // Tăng version lên 2
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ArticleDao articleDao();
    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "newsreader_db")
                    .fallbackToDestructiveMigration() // Nếu không cần dữ liệu cũ
                    .build();
        }
        return instance;
    }
}

