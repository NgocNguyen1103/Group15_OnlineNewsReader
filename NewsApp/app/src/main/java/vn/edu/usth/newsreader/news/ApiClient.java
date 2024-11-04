package vn.edu.usth.newsreader.news;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Lớp ApiClient dùng để khởi tạo và quản lý một instance duy nhất của Retrofit, giúp thực hiện các yêu cầu API.

public class ApiClient {

    // BASE_URL là URL cơ bản của API mà chúng ta sẽ gửi các yêu cầu đến.
    private static final String BASE_URL = "https://newsapi.org/v2/";

    // retrofit là instance duy nhất của Retrofit dùng để thực hiện các yêu cầu API.
    private static Retrofit retrofit = null;

    /**
     * Phương thức này trả về một instance của Retrofit.
     * Nếu Retrofit chưa được khởi tạo, nó sẽ tạo một instance mới với URL và converter được cấu hình.
     * Sau đó, nó sẽ trả về instance đã được khởi tạo, đảm bảo chỉ có một instance duy nhất tồn tại trong ứng dụng.
     *
     * @return instance của Retrofit
     */
    public static Retrofit getClient() {
        // Kiểm tra nếu retrofit là null, có nghĩa là nó chưa được khởi tạo
        if (retrofit == null) {
            // Tạo mới một instance của Retrofit với base URL và converter cho dữ liệu JSON
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Đặt base URL cho Retrofit
                    .addConverterFactory(GsonConverterFactory.create()) // Sử dụng GsonConverterFactory để chuyển đổi JSON sang đối tượng Java
                    .build(); // Xây dựng instance Retrofit
        }
        // Trả về instance Retrofit đã được khởi tạo
        return retrofit;
    }
}


