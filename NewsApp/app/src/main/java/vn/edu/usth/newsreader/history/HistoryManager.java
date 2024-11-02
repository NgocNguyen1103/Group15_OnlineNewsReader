package vn.edu.usth.newsreader.history;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.newsreader.news.Article;

public class HistoryManager {

    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public HistoryManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("read_articles_history", Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    // Lấy dữ liệu lịch sử từ SharedPreferences và chuyển đổi nó từ JSON thành danh sách MutableList<Article>
    public List<Article> getHistory() {
        String json = sharedPreferences.getString("history", null);
        Type type = new TypeToken<ArrayList<Article>>() {}.getType();
        return json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }

    // Thêm bài báo vào lịch sử, nếu chưa tồn tại
    public void addToHistory(Article article) {
        List<Article> history = getHistory();
        boolean exists = false;

        for (Article item : history) {
            if (item.getUrl().equals(article.getUrl())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            history.add(0, article);
            saveHistory(history);
        }
    }

    // Chuyển danh sách lịch sử thành chuỗi JSON và lưu vào SharedPreferences
    private void saveHistory(List<Article> history) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(history);
        editor.putString("history", json);
        editor.apply();
    }

    // Xóa lịch sử
    public void clearHistory() {
        sharedPreferences.edit().remove("history").apply();
    }
}
