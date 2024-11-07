package vn.edu.usth.newsreader.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.db.UserDao;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Kiểm tra nếu cả email và password không rỗng
            // Tạo một luồng mới để xử lý đăng ký trong background thread
            // Lấy instance của UserDao từ cơ sở dữ liệu
            // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
            // Nếu email chưa tồn tại, tạo đối tượng User mới và thêm vào cơ sở dữ liệu
            // Hiển thị thông báo đăng ký thành công trên UI thread
            // Kết thúc Activity sau khi hoàn tất đăng ký
            // Nếu email đã tồn tại, hiển thị thông báo lỗi trên UI thread
            // Nếu email hoặc password bị rỗng, hiển thị thông báo yêu cầu nhập đầy đủ thông tin

            if (!email.isEmpty() && !password.isEmpty()) {
                new Thread(() -> {
                    UserDao userDao = AppDatabase.getInstance(getApplicationContext()).userDao();
                    if (!userDao.checkUserExists(email)) {
                        userDao.insertUser(new User(email, password, false)); // false cho trạng thái chưa đăng nhập
                        runOnUiThread(() -> Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show());
                        finish();
                    } else {
                        runOnUiThread(() -> Toast.makeText(this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show());
                    }
                }).start(); // Bắt đầu thực thi luồng
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
