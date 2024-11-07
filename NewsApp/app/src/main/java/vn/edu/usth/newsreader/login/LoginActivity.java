package vn.edu.usth.newsreader.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;

import vn.edu.usth.newsreader.MainActivity;
import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.db.UserDao;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, anonymousLoginButton;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpTextView);
        anonymousLoginButton = findViewById(R.id.anonymousLoginButton);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Kiểm tra nếu email và password không rỗng
            // Sử dụng Executor để xử lý logic trong background thread
            // Lấy instance của UserDao từ cơ sở dữ liệu
            // Kiểm tra thông tin đăng nhập bằng cách gọi phương thức login
            // Nếu đăng nhập thành công, cập nhật trạng thái đăng nhập của người dùng
            // Chuyển hướng về MainActivity trên luồng chính (UI Thread)
            // Nếu thông tin đăng nhập không đúng, hiển thị thông báo lỗi trên luồng chính
            // Nếu email hoặc password bị rỗng, hiển thị thông báo yêu cầu nhập đầy đủ thông tin

            if (!email.isEmpty() && !password.isEmpty()) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    UserDao userDao = AppDatabase.getInstance(this).userDao();
                    User user = userDao.login(email, password);

                    if (user != null) {
                        user.setLoggedIn(true); // Đặt trạng thái loggedIn thành true
                        userDao.updateUser(user);   // Cập nhật lại thông tin người dùng trong cơ sở dữ liệu

                        runOnUiThread(() -> {
                            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, MainActivity.class));
                            finish(); // Kết thúc Activity hiện tại để không quay lại màn hình đăng nhập
                        });
                    } else {
                        runOnUiThread(() -> Toast.makeText(this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show());
                    }
                });
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
        signUpTextView.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }
}
