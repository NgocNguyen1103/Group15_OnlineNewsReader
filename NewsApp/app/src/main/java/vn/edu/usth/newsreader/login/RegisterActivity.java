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

            if (!email.isEmpty() && !password.isEmpty()) {
                new Thread(() -> {
                    UserDao userDao = AppDatabase.getInstance(getApplicationContext()).userDao();

                    // Kiểm tra nếu người dùng đã tồn tại
                    if (!userDao.checkUserExists(email)) {
                        userDao.insertUser(new User(email, password, false));
                        runOnUiThread(() -> Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show());
                        finish();
                    } else {
                        runOnUiThread(() -> Toast.makeText(this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
