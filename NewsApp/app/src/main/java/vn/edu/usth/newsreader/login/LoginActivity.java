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

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase auth
        auth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.emailEditText);
        EditText password = findViewById(R.id.passwordEditText);
        Button login = findViewById(R.id.loginButton);
        TextView signUp = findViewById(R.id.signUpTextView);
        Button anonymousLoginButton = findViewById(R.id.anonymousLoginButton);

        // Xử lý sự kiện đăng nhập
        login.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            if (!emailText.isEmpty() && !passwordText.isEmpty()) {
                auth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Sign in not successful", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(LoginActivity.this, "Information not complete", Toast.LENGTH_SHORT).show();
            }
        });

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        anonymousLoginButton.setOnClickListener(v -> {
            auth.signInAnonymously()
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            // Chuyển đến màn hình chính
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast.makeText(LoginActivity.this, "Login not successful: " + (e != null ? e.getMessage() : "Unknown error"), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
