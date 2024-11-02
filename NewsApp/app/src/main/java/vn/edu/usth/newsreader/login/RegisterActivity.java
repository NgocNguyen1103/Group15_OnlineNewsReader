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

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo Firebase Auth
        auth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.emailEditText);
        EditText password = findViewById(R.id.passwordEditText);
        Button register = findViewById(R.id.registerButton);

        // Xử lý sự kiện đăng ký
        register.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            if (!emailText.isEmpty() && !passwordText.isEmpty()) {
                auth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                // Chuyển tới màn hình chính
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register not successful", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
