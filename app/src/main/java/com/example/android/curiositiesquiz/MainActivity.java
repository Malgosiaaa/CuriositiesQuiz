package com.example.android.curiositiesquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // declaration of all global variables
    int score = 0;

    EditText userName;

    RadioButton first_button_in_first_question;
    RadioButton second_answer_in_second_question;
    RadioButton fourth_answer_in_third_question;
    CheckBox first_answer_in_fourth_question;
    CheckBox second_answer_in_fourth_question;
    CheckBox third_answer_in_fourth_question;
    CheckBox fourth_answer_in_fourth_question;
    RadioButton third_answer_in_fifth_question;
    EditText sixthQuestion;

    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;
    RadioGroup radioGroup4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of edit text with user's name
        userName = findViewById(R.id.user_name);

        //initialization of all answers
        first_button_in_first_question = findViewById(R.id.first_button_in_first_question);
        second_answer_in_second_question = findViewById(R.id.second_answer_in_second_question);
        fourth_answer_in_third_question = findViewById(R.id.fourth_answer_in_third_question);
        first_answer_in_fourth_question = findViewById(R.id.first_answer_in_fourth_question);
        second_answer_in_fourth_question = findViewById(R.id.second_answer_in_fourth_question);
        third_answer_in_fourth_question = findViewById(R.id.third_answer_in_fourth_question);
        fourth_answer_in_fourth_question = findViewById(R.id.fourth_answer_in_fourth_question);
        third_answer_in_fifth_question = findViewById(R.id.third_answer_in_fifth_question);
        sixthQuestion = findViewById(R.id.sixth_question);

        //initialization of Radio Groups needed in "reset" method
        radioGroup1 = findViewById(R.id.first_radio_group);
        radioGroup2 = findViewById(R.id.second_radio_group);
        radioGroup3 = findViewById(R.id.third_radio_group);
        radioGroup4 = findViewById(R.id.fourth_radio_group);
    }

    /**
     * This method is called after pressing get my score button and gives different toast messages according to earned points number
     */

    public void getScore (View view) {
        //gets user's name to String
        String name = userName.getText().toString();

        //creates score message
        int finalScore = calculateScore(score);
        String scoreMessage = name + getString(R.string.thanks);
        scoreMessage += "\n" + getString(R.string.earned) + finalScore + getString(R.string.points);

        //creates different toast messages according to user's score
        if (finalScore <= 2) {
            Toast.makeText(this, scoreMessage + "\n" + getString(R.string.try_again), Toast.LENGTH_LONG).show();
        } else if (finalScore <= 4) {
            Toast.makeText(this, scoreMessage + "\n" + getString(R.string.good_job), Toast.LENGTH_LONG).show();
        } else if (finalScore <= 6) {
            Toast.makeText(this, scoreMessage + "\n" + getString(R.string.quiz_master), Toast.LENGTH_LONG).show();
        }
    }

    /**
 * This method checks if answers are correct and calculates the user's score
 */
    public int calculateScore(int score) {

        //First question - correct is first answer
        boolean firstQuestion = first_button_in_first_question.isChecked();
        if (firstQuestion == true) {
            score += 1;
        }

        //Second question - correct is second answer
        boolean secondQuestion = second_answer_in_second_question.isChecked();
        if (secondQuestion == true) {
            score += 1;
        }

        //Third question - correct is fourth answer
        boolean thirdQuestion = fourth_answer_in_third_question.isChecked();
        if (thirdQuestion == true) {
            score += 1;
        }

        //Fourth question - correct are first and third answers
        boolean fourthQuestionA1 = first_answer_in_fourth_question.isChecked();
        boolean fourthQuestionA2 = second_answer_in_fourth_question.isChecked();
        boolean fourthQuestionA3 = third_answer_in_fourth_question.isChecked();
        boolean fourthQuestionA4 = fourth_answer_in_fourth_question.isChecked();
        if (fourthQuestionA1 && !fourthQuestionA2 && fourthQuestionA3 && !fourthQuestionA4) {
            score += 1;
        }

        //Fifth question - correct is third answer
        boolean fifthQuestion = third_answer_in_fifth_question.isChecked();
        if (fifthQuestion == true) {
            score += 1;
        }

        //Sixth question - right answer is: Canada
        String sixthQuestionAnswer = sixthQuestion.getText().toString();
        if (sixthQuestionAnswer.equals(getString(R.string.sixth_answer_low))
                || sixthQuestionAnswer.equals(getString(R.string.sixth_answer))
                || sixthQuestionAnswer.equals(getString(R.string.sixth_answer_pl))
                || sixthQuestionAnswer.equals(R.string.sixth_answer_pl_low)) {
            score += 1;
        }

        return (score);
    }

    public void reset (View view) {
        //resets all given answers
        radioGroup1.clearCheck();
        radioGroup2.clearCheck();
        radioGroup3.clearCheck();
        first_answer_in_fourth_question.setChecked(false);
        second_answer_in_fourth_question.setChecked(false);
        third_answer_in_fourth_question.setChecked(false);
        fourth_answer_in_fourth_question.setChecked(false);
        radioGroup4.clearCheck();
        sixthQuestion.setText("");

        score = 0;
    }

    public void seeAnswers (View view) {
        //displays toast message with correct answers
        Toast.makeText(this, "1) " + getString(R.string.first_answer_in_first_question)
                + "\n" + "2) " + getString(R.string.second_answer_in_second_question)
                + "\n" + "3) " + getString(R.string.fourth_answer_in_third_question)
                + "\n" + "4) " + getString(R.string.first_answer_in_fourth_question) + " & " + getString(R.string.third_answer_in_fourth_question)
                + "\n" + "5) " + getString(R.string.third_answer_in_fifth_question)
                + "\n" + "6) " + getString(R.string.sixth_answer), Toast.LENGTH_LONG).show();

    }

}
