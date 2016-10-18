package com.example.android.newbornnumbers;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WeightChange extends NavbarActivity implements View.OnClickListener {

    Button submitButton;
    EditText birthText;
    EditText dayOneText;
    EditText dayTwoText;
    EditText dayNText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.weight_main);

        getLayoutInflater().inflate(R.layout.activity_main, frameLayout);

        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);

        submitButton = (Button) findViewById(R.id.submitWeightButton);

        submitButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        birthText = (EditText) findViewById(R.id.birthWeightInput);
        dayOneText = (EditText) findViewById(R.id.dayOneWeightInput);
        dayTwoText = (EditText) findViewById(R.id.dayTwoWeightInput);
        dayNText = (EditText) findViewById(R.id.dayNWeightInput);

        TextView birthWeightPercentLabel = (TextView) findViewById(R.id.birthToDayNWeightChangeLabel);
        TextView dayOnePercentLabel = (TextView) findViewById(R.id.dayOneWeightChangeLabel);
        TextView dayTwoPercentLabel = (TextView) findViewById(R.id.dayTwoWeightChangeLabel);
        TextView dayNPercentLabel = (TextView) findViewById(R.id.dayNWeightChangeLabel);

        double birthWeightInput = 0;
        double dayOneInput = 0;
        double dayTwoInput = 0;
        double dayNInput = 0;

        if (birthText.getText().toString().matches("")) {
            showErrorMessage(R.string.birth_weight_error_label);
            return;
        } else {
            birthWeightInput = Double.parseDouble(birthText.getText().toString());
            if (birthWeightInput <= 0) {
                showErrorMessage(R.string.birth_weight_error_label);
                return;
            }
        }

        if (dayOneText.getText().toString().matches("") && !birthText.getText().toString().matches("")) {
            showErrorMessage(R.string.day_one_error_label);
            return;
        } else {
            dayOneInput = Double.parseDouble(dayOneText.getText().toString());
            if (dayOneInput <= 0) {
                showErrorMessage(R.string.day_one_error_label);
                return;
            }
            String percentage = Double.toString(weightChangePercentage(birthWeightInput, dayOneInput));
            dayOnePercentLabel.setText("Birth - Day 1 Weight Change: " + percentage + "%");
            dayOnePercentLabel.setVisibility(TextView.VISIBLE);
        }

        if (dayTwoText.getText().toString().matches("") && !dayOneText.getText().toString().matches("")) {
            showErrorMessage(R.string.day_two_error_label);
            return;
        } else {
            dayTwoInput = Double.parseDouble(dayTwoText.getText().toString());
            if (dayTwoInput == 0) {
                showErrorMessage(R.string.day_two_error_label);
                return;
            }
            String percentage = Double.toString(weightChangePercentage(dayOneInput, dayTwoInput));
            dayTwoPercentLabel.setText("Day 1 - Day 2 Weight Change: " + percentage + "%");
            dayTwoPercentLabel.setVisibility(TextView.VISIBLE);
        }

        if (dayNText.getText().toString().matches("") && !dayTwoText.getText().toString().matches("")) {
            showErrorMessage(R.string.day_n_error_label);
            return;
        } else {
            dayNInput = Double.parseDouble(dayNText.getText().toString());
            if (dayNInput <= 0) {
                showErrorMessage(R.string.day_n_error_label);
            } else {
                String percentage = Double.toString(weightChangePercentage(dayTwoInput, dayNInput));
                dayNPercentLabel.setText("Day 2 - Day n Weight Change: " + percentage + "%");
                dayNPercentLabel.setVisibility(TextView.VISIBLE);
            }
        }

        if (birthText.getText().toString().matches("") && !dayNText.getText().toString().matches("")) {
            showErrorMessage(R.string.birth_weight_error_label);
            return;
        }
        else if (!birthText.getText().toString().matches("") && dayNText.getText().toString().matches("")){
            showErrorMessage(R.string.day_n_error_label);
            return;
        }
        else {
            dayNInput = Double.parseDouble(dayNText.getText().toString());
            birthWeightInput = Double.parseDouble(birthText.getText().toString());

            if (dayNInput <= 0) {
                showErrorMessage(R.string.day_n_error_label);
                return;
            }
            else if (birthWeightInput <= 0){
                showErrorMessage(R.string.birth_weight_error_label);
                return;
            }
                String percentage = Double.toString(weightChangePercentage(birthWeightInput, dayNInput));
                birthWeightPercentLabel.setText("Birth - Day n Weight Change: " + percentage + "%");
                birthWeightPercentLabel.setVisibility(TextView.VISIBLE);
        }
    }

    protected double weightChangePercentage(double startingWeight, double endingWeight) {

        double weightChange = Math.round(endingWeight / startingWeight * 100);
        return weightChange;
    }

    protected void showErrorMessage(int idOfStringToDisplay){

        AlertDialog alertDialog = new AlertDialog.Builder(
            WeightChange.this).create();
        alertDialog.setTitle("Invalid Input");

        if (idOfStringToDisplay == R.string.day_one_error_label)
            alertDialog.setMessage(getText(R.string.day_one_error_label));
        else if (idOfStringToDisplay == R.string.day_n_error_label)
            alertDialog.setMessage(getText(R.string.day_n_error_label));
        else if (idOfStringToDisplay == R.string.day_two_error_label)
            alertDialog.setMessage(getText(R.string.day_two_error_label));

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}
