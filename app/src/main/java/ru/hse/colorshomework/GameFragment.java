package ru.hse.colorshomework;

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

public class GameFragment extends Fragment {

    ColorRepository colorRepository;
    ImageView firstSquare, secondSquare, thirdSquare, resSquare;
    TextView scoreView;
    int answer;

    public interface IListener {
        public void onColorClicked(int number);
    }
    protected IListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (requireActivity() instanceof IListener) {
            mListener = (IListener) requireActivity();
        }
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

        colorRepository = new ColorRepository();
        ColorItem colorItem1 = colorRepository.item(0); colorItem1.show(firstSquare);
        ColorItem colorItem2 = colorRepository.item(1); colorItem2.show(secondSquare);
        ColorItem colorItem3 = colorRepository.item(2); colorItem3.show(thirdSquare);
        resSquare.setColorFilter(Color.rgb(colorItem3.getR_component(), colorItem3.getG_component(), colorItem3.getB_component()));
        answer=2;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}