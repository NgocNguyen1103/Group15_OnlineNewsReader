package vn.edu.usth.newsreader.news;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.newsreader.R;

public class aDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WebView webView = findViewById(R.id.webView);

        String url = getIntent().getStringExtra("url"); // Nhận URL từ Intent

        webView.setWebViewClient(new WebViewClient()); // Sử dụng WebViewClient để mở URL trong WebView thay vì mở trong trình duyệt

        webView.getSettings().setJavaScriptEnabled(true); // Bật hỗ trợ JavaScript cho WebView

        if (url != null) {
            webView.loadUrl(url); // Nếu URL hợp lệ, tải URL vào WebView
        }
    }
}


