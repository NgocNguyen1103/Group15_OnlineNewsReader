package vn.edu.usth.newsreader;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.Executors;

import vn.edu.usth.newsreader.db.AppDatabase;
import vn.edu.usth.newsreader.login.LoginActivity;
import vn.edu.usth.newsreader.login.User;
import vn.edu.usth.newsreader.db.UserDao;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavController navController;
    private static final int REQUEST_CALL_PERMISSION = 1;


    @Override
    protected void onStart() {
        super.onStart();

        // Truy vấn người dùng hiện tại từ Room Database
        Executors.newSingleThreadExecutor().execute(() -> {
            UserDao userDao = AppDatabase.getInstance(this).userDao();
            User currentUser = userDao.getLoggedInUser();

            if (currentUser == null || !currentUser.isLoggedIn()) {
                // Chuyển hướng về LoginActivity nếu chưa đăng nhập
                runOnUiThread(() -> {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                });
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kiểm tra người dùng đã đăng nhập
        checkIfUserLoggedIn();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // Thiết lập Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập DrawerLayout và NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // Hiển thị nút mở navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24); // Thay thế bằng icon menu của bạn
        }

        // Xử lý sự kiện khi chọn các item trong Navigation Drawer
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();

            if (itemId == R.id.nav_tin_moi_nhat) {
                navController.popBackStack(R.id.navigation_new, false); // Điều hướng đến HomeFragment nếu đang ở fragment khác
            } else if (itemId == R.id.nav_gui_y_kien) {
                dialPhoneNumber("5554");
            } else if (itemId == R.id.nav_thoat) {
                signOutUser();
            } else if (itemId == R.id.nav_lich_su) {
                Log.d("MainActivity", "Attempting to navigate to HistoryFragment");
                navController.navigate(R.id.historyFragment);
            }

            // Đóng Navigation Drawer sau khi chọn
            drawerLayout.closeDrawers();
            return true;
        });
    }

    /**
     * Kiểm tra xem người dùng đã đăng nhập chưa.
     * Nếu chưa đăng nhập, chuyển hướng tới màn hình LoginActivity.
     */
    private void checkIfUserLoggedIn() {
        AppDatabase db = AppDatabase.getInstance(this);
        Executors.newSingleThreadExecutor().execute(() -> {
            User currentUser = db.userDao().getLoggedInUser();
            if (currentUser == null) {
                runOnUiThread(() -> {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                });
            }
        });
    }


    /**
     * Đăng xuất người dùng hiện tại.
     * Xóa trạng thái đăng nhập trong Room Database và chuyển hướng về LoginActivity.
     */
    private void signOutUser() {
        Executors.newSingleThreadExecutor().execute(() -> {
            UserDao userDao = AppDatabase.getInstance(this).userDao();
            User currentUser = userDao.getLoggedInUser();

            if (currentUser != null) {
                currentUser.setLoggedIn(false); // Cập nhật trạng thái đăng xuất
                userDao.updateUser(currentUser); // Lưu vào cơ sở dữ liệu
            }

            // Chuyển hướng về LoginActivity
            runOnUiThread(() -> {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        });
    }



    /**
     * Gọi một số điện thoại.
     *
     * @param phoneNumber Số điện thoại cần gọi.
     */
    private void dialPhoneNumber(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dialPhoneNumber("0915331999");
            } else {
                Toast.makeText(this, "Quyền thực hiện cuộc gọi đã bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
