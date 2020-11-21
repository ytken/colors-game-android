package ru.hse.colorshomework;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Random;

public class GameFragment extends Fragment {

    protected static final String EXTRAS = "SCORE";

    public interface FragmentListener {
        public void newFragment(int curScore);
    }

    FragmentListener fragmentListener;

    ColorRepository colorRepository;
    ImageView firstSquare, secondSquare, thirdSquare, resSquare;
    TextView scoreView;
    int key=-1, answer=0, score, out = 0;

    public static GameFragment newInstance(int curScore) {
        // Создаем данные, которые потом положим в фрагмент
        final Bundle extras = new Bundle();
        extras.putSerializable(EXTRAS,  curScore);

        final GameFragment fragment = new GameFragment();
        fragment.setArguments(extras);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstSquare = getView().findViewById(R.id.imageView_1);


        secondSquare = getView().findViewById(R.id.imageView_2);


        thirdSquare = getView().findViewById(R.id.imageView_3);

        resSquare = getView().findViewById(R.id.imageView_result);

        scoreView = getView().findViewById(R.id.score_number);
        if (getArguments() != null) {
            final Serializable extras_number = getArguments().getSerializable(EXTRAS);
            if (extras_number instanceof Integer)
                score = (Integer) extras_number;
        }
        scoreView.setText("Score: "+score);

        colorRepository = new ColorRepository();
        ColorItem colorItem1 = colorRepository.item(0); colorItem1.show(firstSquare);
        ColorItem colorItem2 = colorRepository.item(1); colorItem2.show(secondSquare);
        ColorItem colorItem3 = colorRepository.item(2); colorItem3.show(thirdSquare);
        Random randomizer = new Random();
        key = randomizer.nextInt(3) + 1;
        switch (key){
            case 1:
                colorItem1.show(resSquare);
                break;
            case 2:
                colorItem2.show(resSquare);
                break;
            case 3:
                colorItem3.show(resSquare);
                break;
        }

        firstSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key==1){
                    rightAnswer();
                }
                else
                    wrongAnswer();
            }
        });

        secondSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key==2){
                    rightAnswer();
                }
                else
                    wrongAnswer();
            }
        });

        thirdSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key==3){
                    rightAnswer();
                }
                else
                    wrongAnswer();
            }
        });

    }
    public void rightAnswer() {
        Toast.makeText(getContext(), "You're right!\n + 1", Toast.LENGTH_SHORT).show();
        score++;
        fragmentListener.newFragment(score);
    }

    public void wrongAnswer() {
        Toast.makeText(getContext(), "Noooo, the right one is " + key, Toast.LENGTH_SHORT).show();

        score--;
        fragmentListener.newFragment(score);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            fragmentListener = (FragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmentListener");
        }
    }
}