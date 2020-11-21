package ru.hse.colorshomework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements GameFragment.FragmentListener{

    protected static final String TAG_CUR_FRAGMENT = "CUR_FRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button back = findViewById(R.id.button_back);
        GameActivity thisActivity = this;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, MainActivity.class);
                startActivity(intent);
            }
        });

        GameFragment gameFragment = GameFragment.newInstance(0);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment, gameFragment).addToBackStack(TAG_CUR_FRAGMENT).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void newFragment(int curScore) {
        GameFragment gameFragment = GameFragment.newInstance(curScore);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, gameFragment, TAG_CUR_FRAGMENT).addToBackStack(TAG_CUR_FRAGMENT).commit();
    }
}