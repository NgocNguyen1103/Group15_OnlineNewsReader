package vn.edu.usth.newsreader.nghia_works;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.newsreader.R;

public class Activity_setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting_nghia);

    }
    public void clickNotificationnghia(View view) {
        // Khởi tạo Intent để mở SecondActivity
        Intent intent = new Intent(Activity_setting.this, Activity_setting_noti.class);
        startActivity(intent);
    }
}