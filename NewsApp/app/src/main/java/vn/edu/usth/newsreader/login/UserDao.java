package vn.edu.usth.newsreader.login;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    // Kiểm tra xem người dùng có tồn tại dựa trên email
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    boolean checkUserExists(String email);

    // Thêm phương thức login
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    User login(String email, String password);
    // Lấy người dùng đang đăng nhập
    @Query("SELECT * FROM users WHERE isLoggedIn = 1 LIMIT 1")
    User getLoggedInUser();

    // Cập nhật thông tin người dùng
    @Update
    void updateUser(User user);

    // Thêm người dùng mới
    @Insert
    void insertUser(User user);
}

