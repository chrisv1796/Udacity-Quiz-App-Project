package com.example.leaguequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitButton(View view) {

        Boolean allEditFieldsAreFilled = allEditTextsAreFilled();

        if (allEditFieldsAreFilled) {
            String resultsString = quizResults();
            String correctAnsers = answerChecker();

            Context toastContext = getApplicationContext();
            CharSequence toastText = resultsString + "\nAnd you got " + correctAnsers + " answer(s) correct!";
            int toastDuration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(toastContext, toastText, toastDuration);
            toast.show();
        }
        if (allEditFieldsAreFilled == false) {
            Toast failedToast = Toast.makeText(getApplicationContext(), "Please Fill Out All Fields", Toast.LENGTH_LONG);
            failedToast.show();

        }

    }

    private String quizResults() {

        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        EditText favChampField = (EditText) findViewById(R.id.favChampField);
        String favChamp = favChampField.getText().toString();

        EditText highestRank = (EditText) findViewById(R.id.highestRankQuestion);
        String favRole = highestRank.getText().toString();

        String resultsString = "Hey, " + name + "my have champion is also " + favChamp;

        return resultsString;
    }

    private String answerChecker() {

        int correctAnswerCounter = 0;
        String correctAnswers = "";

        RadioButton questionThree = (RadioButton) findViewById(R.id.twoThousandNineRadioButton);
        RadioButton questionFour = (RadioButton) findViewById(R.id.singedRadioButton);

        Boolean questionOneCorrect = highestRankQuestionIsCorrect();
        Boolean questionFourCorrect = allCheckBoxesSelected();

        if (questionFourCorrect) {
            correctAnswerCounter += 1;
        }

        if (questionOneCorrect) {
            correctAnswerCounter += 1;
        }

        if (questionThree.isChecked()) {
            correctAnswerCounter += 1;
        }
        if (questionFour.isChecked()) {
            correctAnswerCounter += 1;
        }
        if (correctAnswerCounter == 4) {
            correctAnswers = "all";
        }
        if (correctAnswerCounter == 3) {
            correctAnswers = "three";
        }
        if (correctAnswerCounter == 2) {
            correctAnswers = "two";
        }
        if (correctAnswerCounter == 1) {
            correctAnswers = "one";
        }
        if (correctAnswerCounter == 0) {
            correctAnswers = "none";
        }

        return correctAnswers;
    }

    private Boolean allEditTextsAreFilled() {
        Boolean theyAreFilled = false;

        EditText nameField = (EditText) findViewById(R.id.nameField);
        EditText favChampField = (EditText) findViewById(R.id.favChampField);
        EditText highestRank = (EditText) findViewById(R.id.highestRankQuestion);

        String name = nameField.getText().toString();
        String champ = favChampField.getText().toString();
        String favRole = highestRank.getText().toString();

        if (name.isEmpty() == false) {
            if (champ.isEmpty() == false) {
                if (favRole.isEmpty() == false) {
                    theyAreFilled = true;
                }else {
                    theyAreFilled = false;
                }
            }
        }
        if (name.isEmpty()) {
            if (champ.isEmpty()) {
                if (favRole.isEmpty()) {
                    theyAreFilled = false;
                }
            }
        }
        return theyAreFilled;
    }

    private Boolean highestRankQuestionIsCorrect() {
        EditText highestRank = (EditText) findViewById(R.id.highestRankQuestion);
        boolean answer = highestRank.getText().toString().trim().toUpperCase().equals("CHALLENGER");
        if (answer) {
            System.out.println("YOU GOT IT RIGHT");
            return true;
        }else {
            return false;
        }
    }

    private Boolean allCheckBoxesSelected() {
        CheckBox healCheckBox = (CheckBox)findViewById(R.id.healCheckBox);
        CheckBox igniteCheckBox = (CheckBox)findViewById(R.id.igniteCheckBox);
        CheckBox teleportCheckBox = (CheckBox)findViewById(R.id.teleportCheckBox);

        if (healCheckBox.isChecked()){
            if (igniteCheckBox.isChecked()){
                if (teleportCheckBox.isChecked()){
                    return true;
                }else return false;
            }
        }
    return false;
    }

}