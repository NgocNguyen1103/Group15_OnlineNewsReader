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

        WebView webView = findViewById(R.id.gameWebView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Kích hoạt JavaScript nếu trò chơi yêu cầu

        webView.setWebViewClient(new WebViewClient());

        String gameUrl = getIntent().getStringExtra("gameUrl");
        if (gameUrl != null) {
            webView.loadUrl(gameUrl);
        }
    }
}
