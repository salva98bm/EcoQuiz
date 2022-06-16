package es.studium.ecoquizz.menu_fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.MenuActivity;
import es.studium.ecoquizz.crud.UpdatePlayer;
import es.studium.ecoquizz.dialogs.ScoreDialog;
import es.studium.ecoquizz.etc.Methods;
import es.studium.ecoquizz.models.Question;

public class PlayFragment extends Fragment implements View.OnClickListener {

    ArrayList<Question> questions = new ArrayList<Question>();
    TextView textView_questions, textView_score;
    Button button_answer1, button_answer2, button_answer3, button_answer4, button_next;
    int score, correctAnswer, arrayIterator;
    String sound;

    public PlayFragment() {
        score = 0;
        arrayIterator = 0;
        correctAnswer = 0;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_fragment, container, false);

        questions = Methods.getQuestions();

        textView_questions = view.findViewById(R.id.textView_question);
        textView_score = view.findViewById(R.id.textView_play_score);
        textView_score.setText(Integer.toString(score));
        button_answer1 = view.findViewById(R.id.button_answer1);
        button_answer1.setOnClickListener(this);
        button_answer2 = view.findViewById(R.id.button_answer2);
        button_answer2.setOnClickListener(this);
        button_answer3 = view.findViewById(R.id.button_answer3);
        button_answer3.setOnClickListener(this);
        button_answer4 = view.findViewById(R.id.button_answer4);
        button_answer4.setOnClickListener(this);
        button_next = view.findViewById(R.id.button_next);
        button_next.setOnClickListener(this);
        controlClick(true);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        sound = sharedPreferences.getString("sound", "true");

        textView_questions.setText(questions.get(arrayIterator).getEnunciado());
        loadQuestion();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_answer1:
                validateAnswer(1);
                break;
            case R.id.button_answer2:
                validateAnswer(2);
                break;
            case R.id.button_answer3:
                validateAnswer(3);
                break;
            case R.id.button_answer4:
                validateAnswer(4);
                break;
            case R.id.button_next:
                if(arrayIterator<questions.size()){
                    controlClick(true);
                    textView_score.setText(Integer.toString(score));
                    loadQuestion();
                    resetColors();
                    button_next.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    public void loadQuestion(){

        int num1, num2, num3, num4;

        do{
            Random random = new Random();
            num1 = random.nextInt(4) + 1;
            num2 = random.nextInt(4) + 1;
            num3 = random.nextInt(4) + 1;
            num4 = random.nextInt(4) + 1;
        }while((num1==num2) || (num1==num3) || (num1==num4) || (num2==num3) ||
                (num2==num4) || (num3==num4));

        textView_questions.setText(questions.get(arrayIterator).getEnunciado());

        switch (num1){
            case 1:
                button_answer1.setText(questions.get(arrayIterator).getRespuesta1());
                correctAnswer = 1;
                break;
            case 2:
                button_answer1.setText(questions.get(arrayIterator).getRespuesta2());
                break;
            case 3:
                button_answer1.setText(questions.get(arrayIterator).getRespuesta3());
                break;
            case 4:
                button_answer1.setText(questions.get(arrayIterator).getRespuesta4());
                break;
        }

        switch (num2){
            case 1:
                button_answer2.setText(questions.get(arrayIterator).getRespuesta1());
                correctAnswer = 2;
                break;
            case 2:
                button_answer2.setText(questions.get(arrayIterator).getRespuesta2());
                break;
            case 3:
                button_answer2.setText(questions.get(arrayIterator).getRespuesta3());
                break;
            case 4:
                button_answer2.setText(questions.get(arrayIterator).getRespuesta4());
                break;
        }

        switch (num3){
            case 1:
                button_answer3.setText(questions.get(arrayIterator).getRespuesta1());
                correctAnswer = 3;
                break;
            case 2:
                button_answer3.setText(questions.get(arrayIterator).getRespuesta2());
                break;
            case 3:
                button_answer3.setText(questions.get(arrayIterator).getRespuesta3());
                break;
            case 4:
                button_answer3.setText(questions.get(arrayIterator).getRespuesta4());
                break;
        }

        switch (num4){
            case 1:
                button_answer4.setText(questions.get(arrayIterator).getRespuesta1());
                correctAnswer = 4;
                break;
            case 2:
                button_answer4.setText(questions.get(arrayIterator).getRespuesta2());
                break;
            case 3:
                button_answer4.setText(questions.get(arrayIterator).getRespuesta3());
                break;
            case 4:
                button_answer4.setText(questions.get(arrayIterator).getRespuesta4());
                break;
        }
        arrayIterator++;
    }

    @SuppressLint("ResourceAsColor")
    public void validateAnswer(int answer) {
        switch (answer){
            case 1:
                if(correctAnswer==1){
                    if(sound.equals("true")){
                        Methods.successSound(getContext());
                    }
                    button_answer1.setBackgroundColor(getResources().getColor(R.color.principal_green));
                    button_answer1.setTextColor(getResources().getColor(R.color.cream));
                    button_next.setVisibility(View.VISIBLE);
                    score++;
                }else{
                    if(sound.equals("true")){
                        Methods.errorSound(getContext());
                    }
                    loadFragment(false);
                }
                break;
            case 2:
                if(correctAnswer==2){
                    if(sound.equals("true")){
                        Methods.successSound(getContext());
                    }
                    button_answer2.setBackgroundColor(getResources().getColor(R.color.principal_green));
                    button_answer2.setTextColor(getResources().getColor(R.color.cream));
                    button_next.setVisibility(View.VISIBLE);
                    score++;
                }else{
                    if(sound.equals("true")){
                        Methods.errorSound(getContext());
                    }
                    loadFragment(false);
                }
                break;
            case 3:
                if(correctAnswer==3){
                    if(sound.equals("true")){
                        Methods.successSound(getContext());
                    }
                    button_answer3.setBackgroundColor(getResources().getColor(R.color.principal_green));
                    button_answer3.setTextColor(getResources().getColor(R.color.cream));
                    button_next.setVisibility(View.VISIBLE);
                    score++;
                }else{
                    if(sound.equals("true")){
                        Methods.errorSound(getContext());
                    }
                    loadFragment(false);
                }
                break;
            case 4:
                if(correctAnswer==4){
                    if(sound.equals("true")){
                        Methods.successSound(getContext());
                    }
                    button_answer4.setBackgroundColor(getResources().getColor(R.color.principal_green));
                    button_answer4.setTextColor(getResources().getColor(R.color.cream));
                    button_next.setVisibility(View.VISIBLE);
                    score++;
                }else{
                    if(sound.equals("true")){
                        Methods.errorSound(getContext());
                    }
                    loadFragment(false);
                }
                break;
        }
        controlClick(false);

        if(arrayIterator==42){
            loadFragment(true);
        }
    }

    private void controlClick(boolean clickable) {
        button_answer1.setClickable(clickable);
        button_answer2.setClickable(clickable);
        button_answer3.setClickable(clickable);
        button_answer4.setClickable(clickable);
    }

    private void loadFragment(boolean max) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_menu, new MenuFragment());

        if(max){
            new ScoreDialog(score, 3).show(getActivity().getSupportFragmentManager(), "ScoreDialog");
            MenuActivity.player.setScore(score);
            new UpdatePlayer(MenuActivity.player).execute();
        }else{
            if(score > MenuActivity.player.getScore()){
                new ScoreDialog(score, 2).show(getActivity().getSupportFragmentManager(), "ScoreDialog");
                MenuActivity.player.setScore(score);
                new UpdatePlayer(MenuActivity.player).execute();
            }else {
                new ScoreDialog(score, 1).show(getActivity().getSupportFragmentManager(), "ScoreDialog");
            }
        }
        fragmentTransaction.commit();
    }

    private void resetColors() {
        button_answer1.setBackgroundColor(getResources().getColor(R.color.cream));
        button_answer1.setTextColor(getResources().getColor(R.color.principal_blue));
        button_answer2.setBackgroundColor(getResources().getColor(R.color.cream));
        button_answer2.setTextColor(getResources().getColor(R.color.principal_blue));
        button_answer3.setBackgroundColor(getResources().getColor(R.color.cream));
        button_answer3.setTextColor(getResources().getColor(R.color.principal_blue));
        button_answer4.setBackgroundColor(getResources().getColor(R.color.cream));
        button_answer4.setTextColor(getResources().getColor(R.color.principal_blue));
    }
}