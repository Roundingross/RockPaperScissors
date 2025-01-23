package com.example.lab1a;


import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab1a.databinding.ActivityMainBinding;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Weapon playerWeapon;
    private final Random random = new Random();
    private int playerScore = 0, computerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Background Video
        VideoView videoView = findViewById(R.id.videoBackground);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_background;
        videoView.setVideoPath(videoPath);
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        videoView.start();
    }

    // Click buttons
    public void onClickRock(View view) {
        playerWeapon = Weapon.ROCK;
        playGame();
    }
    public void onClickPaper(View view) {
        playerWeapon = Weapon.PAPER;
        playGame();
    }
    public void onClickScissor(View view) {
        playerWeapon = Weapon.SCISSORS;
        playGame();
    }

    // Game Logic
    private void playGame() {
        Weapon computerWeapon = generateRandomWeapon();
        StringBuilder result = new StringBuilder();

        result.append("Player chose: ").append(playerWeapon).append("\n").append("Computer chose: ").append(computerWeapon).append("\n").append(determineWinner(playerWeapon, computerWeapon));
        binding.playGameTextView.setText(result.toString());
        binding.scoreCountTextView.setText(getString(R.string.score_display, playerScore, computerScore));
    }

    // Generate random weapon for computer
    private Weapon generateRandomWeapon() {
        Weapon[] weapons = Weapon.values();
        return weapons[random.nextInt(weapons.length)];
    }

    // Game Logic and determining the winner
    private String determineWinner(Weapon player, Weapon computer) {
        int playerOrdinal = player.ordinal();
        int computerOrdinal = computer.ordinal();
        if (playerOrdinal == computerOrdinal) {
            return "It's a tie!";
        } else if ((playerOrdinal + 1) % 3 == computerOrdinal) {
            computerScore++;
            return computer + " beats " + player + ", you lose!";
        } else {
            playerScore++;
            return player + " beats " + computer + ", you win!";
        }
    }
}