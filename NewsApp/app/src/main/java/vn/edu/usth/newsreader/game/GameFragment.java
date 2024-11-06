// vn/edu/usth/newsreader/game/GameFragment.java
package vn.edu.usth.newsreader.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import vn.edu.usth.newsreader.R;

public class GameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        // Game 1
        Button playButton1 = view.findViewById(R.id.playButton1);
        playButton1.setOnClickListener(v -> openGame("https://html5.gamedistribution.com/6b93a7631e0d41d59cd4d661b46ccf90/?gd_sdk_referrer_url=https://gamedistribution.com/games/turbo-race-3d/"));

        // Game 2
        Button playButton2 = view.findViewById(R.id.playButton2);
        playButton2.setOnClickListener(v -> openGame("https://html5.gamedistribution.com/0f294f81b50b4dbfb00a4c30df3573b9/?gd_sdk_referrer_url=https://gamedistribution.com/games/color-maze-1/"));

        // Game 3
        Button playButton3 = view.findViewById(R.id.playButton3);
        playButton3.setOnClickListener(v -> openGame("https://html5.gamedistribution.com/f026d2bf0d8a4e72a14d0db48bbd7bf1/?gd_sdk_referrer_url=https://gamedistribution.com/games/stickman-halloween-survive/"));

        // Game 4
        Button playButton4 = view.findViewById(R.id.playButton4);
        playButton4.setOnClickListener(v -> openGame("https://html5.gamedistribution.com/1984cf24c5994b96b03dc9c174aa4233/?gd_sdk_referrer_url=https://gamedistribution.com/games/duet-cats-halloween-cat-music/"));

        return view;
    }

    private void openGame(String url) {
        Intent intent = new Intent(getActivity(), GameActivity.class); // Tạo một Intent để mở GameActivity
        intent.putExtra("gameUrl", url); // Truyền URL của trò chơi qua Intent
        startActivity(intent); // Bắt đầu GameActivity
    }

}
