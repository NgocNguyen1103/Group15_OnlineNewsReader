package vn.edu.usth.newsreader.news;

import com.google.gson.annotations.SerializedName;

/* Lớp Article ở đây đóng vai trò là một data model đại diện cho một bài báo (article) trong ứng dụng của bạn.
 Đây là nơi lưu trữ các thông tin chính của một bài báo mà bạn có thể nhận từ API hoặc cơ sở dữ liệu.
 1. Các thuộc tính của lớp
Source source: Nguồn gốc của bài báo.
String title: Tiêu đề của bài báo
String description: Mô tả bài báo
String url: Đường dẫn URL đến bài báo đầy đủ.
String urlToImage: Đường dẫn URL đến hình ảnh minh họa của bài báo, thường được dùng để hiển thị thumbnail trong ứng dụng (có thể null nếu không có ảnh).
 */
public class Article {

    private Source source;
    private String title;
    private String description; // có thể là null

    private String url;
    @SerializedName("urlToImage")
    private String urlToImage; // có thể là null

    public Article(Source source, String title, String description, String url, String urlToImage) {
        this.source = source;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}

