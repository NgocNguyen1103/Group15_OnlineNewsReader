package vn.edu.usth.newsreader.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import vn.edu.usth.newsreader.MainActivity;
import vn.edu.usth.newsreader.R;

public class LoginActivity extends AppCompatActivity {

    // Khởi tạo Firebase Authentication
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo Firebase Authentication
        auth = FirebaseAuth.getInstance();

        // Tham chiếu đến các thành phần giao diện người dùng
        EditText email = findViewById(R.id.emailEditText);
        EditText password = findViewById(R.id.passwordEditText);
        Button login = findViewById(R.id.loginButton);
        TextView signUp = findViewById(R.id.signUpTextView);
        Button anonymousLoginButton = findViewById(R.id.anonymousLoginButton);

        // Đặt sự kiện click cho nút đăng nhập để thực hiện đăng nhập với email và mật khẩu
        login.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            // Kiểm tra xem các trường đã được điền hay chưa
            if (!emailText.isEmpty() && !passwordText.isEmpty()) {
                // Thực hiện đăng nhập với email và mật khẩu
                auth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Đăng nhập thành công, chuyển đến MainActivity
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Thông báo lỗi nếu đăng nhập không thành công
                                Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                // Thông báo khi thông tin chưa đầy đủ
                Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        // Đặt sự kiện click cho TextView đăng ký để điều hướng đến màn hình đăng ký
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Đặt sự kiện click cho nút đăng nhập ẩn danh để truy cập không cần tài khoản
        anonymousLoginButton.setOnClickListener(v -> {
            auth.signInAnonymously()
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Đăng nhập ẩn danh thành công, chuyển đến MainActivity
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Thông báo lỗi nếu đăng nhập ẩn danh không thành công
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast.makeText(LoginActivity.this, "Đăng nhập không thành công: " + (e != null ? e.getMessage() : "Lỗi không xác định"), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}

