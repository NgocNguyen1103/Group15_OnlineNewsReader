package vn.edu.usth.newsreader.news;

import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    // Constructor
    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

