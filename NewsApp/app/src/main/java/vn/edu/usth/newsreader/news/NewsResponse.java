package vn.edu.usth.newsreader.news;

import java.util.List;

// Lớp NewsResponse đại diện cho phản hồi từ API khi yêu cầu lấy danh sách các bài báo.
// Nó chứa các thuộc tính phản ánh trạng thái, tổng số kết quả và danh sách các bài báo.
public class NewsResponse {

    // Thuộc tính status đại diện cho trạng thái của phản hồi từ API (ví dụ: "ok" hoặc "error").
    private String status;

    // Thuộc tính totalResults biểu thị tổng số bài báo được trả về từ API.
    private int totalResults;

    // Thuộc tính articles chứa danh sách các bài báo được trả về từ API, mỗi bài báo là một đối tượng Article.
    private List<Article> articles;

    // Constructor
    public NewsResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

