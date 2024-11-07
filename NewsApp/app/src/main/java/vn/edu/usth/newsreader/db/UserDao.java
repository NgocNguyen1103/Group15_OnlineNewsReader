package vn.edu.usth.newsreader.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import vn.edu.usth.newsreader.login.User;

// Annotation @Dao (Data Access Object) cho biết đây là một giao diện để thao tác dữ liệu
// trong bảng của Room Database.
@Dao
public interface UserDao {

    // Kiểm tra xem người dùng có tồn tại dựa trên email
    // Phương thức này sử dụng annotation @Query để thực hiện một truy vấn SQL.
    // Câu truy vấn kiểm tra xem có người dùng nào trong bảng "users" với email được cung cấp hay không.
    // Kết quả trả về là `true` nếu người dùng tồn tại, ngược lại là `false`.
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    boolean checkUserExists(String email);

    // Phương thức đăng nhập
    // Thực hiện truy vấn để lấy thông tin người dùng dựa trên email và password được cung cấp.
    // Câu truy vấn sử dụng "LIMIT 1" để đảm bảo chỉ trả về tối đa một người dùng.
    // Phương thức này được dùng để xác thực thông tin đăng nhập cơ bản.
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    User login(String email, String password);

    // Lấy thông tin người dùng đang đăng nhập
    // Truy vấn này giả định bảng "users" có một cột `isLoggedIn` dùng để đánh dấu trạng thái đăng nhập của người dùng.
    // Phương thức trả về người dùng có `isLoggedIn = 1`.
    // "LIMIT 1" đảm bảo chỉ trả về một bản ghi.
    @Query("SELECT * FROM users WHERE isLoggedIn = 1 LIMIT 1")
    User getLoggedInUser();

    // Cập nhật thông tin người dùng
    // Annotation @Update cung cấp cách dễ dàng để cập nhật bản ghi trong cơ sở dữ liệu.
    // Phương thức này chấp nhận một đối tượng `User` và cập nhật các giá trị tương ứng trong bảng "users".
    @Update
    void updateUser(User user);

    // Thêm một người dùng mới
    // Annotation @Insert cho phép chèn một bản ghi mới vào cơ sở dữ liệu.
    // Phương thức này nhận một đối tượng `User` và thêm vào bảng "users".
    @Insert
    void insertUser(User user);

}


