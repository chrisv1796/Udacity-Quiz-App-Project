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
            String customGreeting = greeting();
            String correctAnswers = howManyCorrectAnswers();
            CharSequence toastText = customGreeting + getString(R.string.and_you_got) + correctAnswers + getString(R.string.answers_correct);
            Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG);
            toast.show();
        }
        if (allEditFieldsAreFilled == false) {
            Toast failedToast = Toast.makeText(getApplicationContext(), getString(R.string.please_fill_out_ALL_fields), Toast.LENGTH_LONG);
            failedToast.show();
        }
    }

    private String greeting() {
        EditText nameField = (EditText) findViewById(R.id.name_edit_text);
        EditText favChampField = (EditText) findViewById(R.id.fav_champ_edit_text);

        String name = nameField.getText().toString();
        String favChamp = favChampField.getText().toString();
        String customGreeting = getString(R.string.hey) + name + getString(R.string.greeting_fav_champ) + favChamp;
        return customGreeting;
    }

    private String howManyCorrectAnswers() {

        int correctAnswerCounter = 0;
        String correctAnswers = "";

        RadioButton questionThreeRadioButton = (RadioButton) findViewById(R.id.twoThousandNineRadioButton);
        RadioButton questionFourRadioButton = (RadioButton) findViewById(R.id.singedRadioButton);

        Boolean questionOneCorrect = highestRankQuestionIsCorrect();
        Boolean questionFourCorrect = allCheckBoxesSelected();

        if (questionFourCorrect) {
            correctAnswerCounter += 1;
        }

        if (questionOneCorrect) {
            correctAnswerCounter += 1;
        }

        if (questionThreeRadioButton.isChecked()) {
            correctAnswerCounter += 1;
        }
        if (questionFourRadioButton.isChecked()) {
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

        EditText nameField = (EditText) findViewById(R.id.name_edit_text);
        EditText favChampField = (EditText) findViewById(R.id.fav_champ_edit_text);
        EditText highestRank = (EditText) findViewById(R.id.highest_rank_question_edit_text);

        String name = nameField.getText().toString();
        String champ = favChampField.getText().toString();
        String favRole = highestRank.getText().toString();

        if (name.isEmpty() == false) {
            if (champ.isEmpty() == false) {
                if (favRole.isEmpty() == false) {
                    theyAreFilled = true;
                } else {
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
        EditText highestRank = (EditText) findViewById(R.id.highest_rank_question_edit_text);
        boolean answer = highestRank.getText().toString().trim().equalsIgnoreCase("challenger");
        if (answer) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean allCheckBoxesSelected() {
        CheckBox healCheckBox = (CheckBox) findViewById(R.id.healCheckBox);
        CheckBox igniteCheckBox = (CheckBox) findViewById(R.id.igniteCheckBox);
        CheckBox teleportCheckBox = (CheckBox) findViewById(R.id.teleportCheckBox);

        if (healCheckBox.isChecked() && igniteCheckBox.isChecked() && teleportCheckBox.isChecked()) {
            return true;
        } else {
            return false;
        }
    }
}