package vn.edu.usth.newsreader.login;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Annotation @Entity đánh dấu lớp này là một bảng trong cơ sở dữ liệu Room
// - tableName: Tên bảng trong cơ sở dữ liệu là "users"
@Entity(tableName = "users")
public class User {

    // Khóa chính của bảng
    // - autoGenerate: Room sẽ tự động tăng giá trị của cột id
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String password;
    private boolean isLoggedIn;
 /*
 Room trong Android, lớp User được khai báo với @Entity sẽ được ánh xạ sang một bảng SQL tương ứng
 CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Khóa chính, tự động tăng
    email TEXT,                            -- Email người dùng (kiểu chuỗi)
    password TEXT,                         -- Mật khẩu người dùng (kiểu chuỗi)
    isLoggedIn INTEGER                     -- Trạng thái đăng nhập (1: true, 0: false)
);*/


    // Constructor, Getters và Setters
    public User(String email, String password, boolean isLoggedIn) {
        this.email = email;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}

