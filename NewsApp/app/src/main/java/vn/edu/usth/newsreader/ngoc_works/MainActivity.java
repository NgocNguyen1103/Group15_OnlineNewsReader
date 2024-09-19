package vn.edu.usth.newsreader.ngoc_works;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.newsreader.R;
import vn.edu.usth.newsreader.databinding.ActivityMainBinding;
import vn.edu.usth.newsreader.nghia_works.MenuFragment;
import vn.edu.usth.newsreader.phuong_works.PodcastFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            replaceFragment(new HomeFragment());

            binding.BottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    replaceFragment(new HomeFragment());
                    return true;
                } else if (itemId == R.id.podcast) {
                    replaceFragment(new PodcastFragment());
                    return true;
                } else if (itemId == R.id.video) {
                    replaceFragment(new VideoFragment());
                    return true;
                } else if (itemId == R.id.menu){
                    replaceFragment(new MenuFragment());
                    return true;
                }
                return false;
            });
            EdgeToEdge.enable(this);


        }
        private void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();;
            fragmentTransaction.replace(R.id.main, fragment);
            fragmentTransaction.commit();

    };
}