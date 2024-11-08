package vn.edu.usth.newsreader.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.Executors;

import vn.edu.usth.newsreader.MainActivity;
import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.db.UserDao;

public class LoginTabFragment extends Fragment {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gắn giao diện cho Fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        // Ánh xạ các thành phần UI
        emailEditText = view.findViewById(R.id.login_email);
        passwordEditText = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);

        // Thiết lập sự kiện cho nút đăng nhập
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    // Lấy UserDao từ AppDatabase
                    UserDao userDao = AppDatabase.getInstance(requireContext()).userDao();
                    User user = userDao.login(email, password);

                    if (user != null) {
                        user.setLoggedIn(true); // Đặt trạng thái loggedIn thành true
                        userDao.updateUser(user); // Cập nhật lại trạng thái đăng nhập trong database

                        // Chuyển hướng về MainActivity trên luồng chính
                        requireActivity().runOnUiThread(() -> {
                            Toast.makeText(requireContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(requireActivity(), MainActivity.class);
                            startActivity(intent);
                            requireActivity().finish(); // Đóng Activity chứa Fragment này
                        });
                    } else {
                        // Thông báo lỗi đăng nhập sai
                        requireActivity().runOnUiThread(() -> Toast.makeText(requireContext(), "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show());
                    }
                });
            } else {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
