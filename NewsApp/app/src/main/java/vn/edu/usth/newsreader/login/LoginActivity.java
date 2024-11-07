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

            if (!email.isEmpty() && !password.isEmpty()) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    UserDao userDao = AppDatabase.getInstance(this).userDao();
                    User user = userDao.login(email, password);

                    if (user != null) {
                        // Cập nhật trạng thái đăng nhập
                        user.setLoggedIn(true);
                        userDao.updateUser(user);

                        // Chuyển hướng về MainActivity
                        runOnUiThread(() -> {
                            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, MainActivity.class));
                            finish();
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
