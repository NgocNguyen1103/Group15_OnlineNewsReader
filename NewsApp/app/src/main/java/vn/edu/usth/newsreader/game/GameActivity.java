// vn/edu/usth/newsreader/game/GameWebViewActivity.java
package vn.edu.usth.newsreader.game;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.usth.newsreader.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Khởi tạo đối tượng WebView và cài đặt các thiết lập cần thiết
        WebView webView = findViewById(R.id.gameWebView); // Liên kết với WebView trong layout

// Khởi tạo đối tượng WebSettings để cấu hình các cài đặt của WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Cho phép JavaScript, nếu trò chơi cần JavaScript để chạy

// Cài đặt WebViewClient cho WebView để mở URL bên trong ứng dụng thay vì mở trong trình duyệt mặc định
        webView.setWebViewClient(new WebViewClient());

// Lấy URL trò chơi từ Intent, URL này được truyền từ Activity trước
        String gameUrl = getIntent().getStringExtra("gameUrl");
        if (gameUrl != null) { // Kiểm tra nếu URL không null
            webView.loadUrl(gameUrl); // Tải URL của trò chơi vào WebView
        }

    }
}
