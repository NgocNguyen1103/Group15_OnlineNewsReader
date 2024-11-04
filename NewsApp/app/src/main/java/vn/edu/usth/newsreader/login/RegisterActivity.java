package vn.edu.usth.newsreader.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

import vn.edu.usth.newsreader.MainActivity;
import vn.edu.usth.newsreader.R;


public class RegisterActivity extends AppCompatActivity {

    // Khai báo Firebase Authentication
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo Firebase Auth để xử lý việc đăng ký
        auth = FirebaseAuth.getInstance();

        // Tham chiếu các thành phần giao diện người dùng từ layout
        EditText email = findViewById(R.id.emailEditText);
        EditText password = findViewById(R.id.passwordEditText);
        Button register = findViewById(R.id.registerButton);

            // Đặt sự kiện click cho nút đăng ký
        register.setOnClickListener(v -> {
            // Lấy email và mật khẩu từ các trường nhập liệu
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            // Kiểm tra xem các trường có rỗng không trước khi đăng ký
            if (!emailText.isEmpty() && !passwordText.isEmpty()) {
                // Thực hiện đăng ký người dùng mới với email và mật khẩu
                auth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Đăng ký thành công, chuyển đến màn hình chính
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Xử lý lỗi khi đăng ký không thành công
                                Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                // Thông báo nếu người dùng chưa nhập đầy đủ thông tin
                Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

