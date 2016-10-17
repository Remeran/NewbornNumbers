package com.example.android.newbornnumbers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 */
public class WeightChange extends NavbarActivity implements View.OnClickListener {

    Button submitButton;
    EditText dayOneText;
    EditText dayTwoText;
    EditText dayNText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.weight_main);
        /**
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.activity_main, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);

        submitButton = (Button) findViewById(R.id.submitWeightButton);

        submitButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        dayOneText = (EditText) findViewById(R.id.dayOneWeightInput);
        dayTwoText = (EditText) findViewById(R.id.dayTwoWeightInput);
        dayNText = (EditText) findViewById(R.id.dayNWeightInput);

        TextView dayOnePercentLabel = (TextView) findViewById(R.id.dayOneWeightChangeLabel);
        TextView dayTwoPercentLabel = (TextView) findViewById(R.id.dayTwoWeightChangeLabel);
        TextView dayNPercentLabel = (TextView) findViewById(R.id.dayNWeightChangeLabel);

        double dayOneInput = 0;
        double dayTwoInput = 0;
        double dayNInput = 0;

        if (dayOneText != null ) {
            dayOneInput = Double.parseDouble(dayOneText.getText().toString());
            //to do: grab the birth weight that was entered in initial input

            if (dayOneInput <=0) {
                showErrorMessage(R.string.day_one_error_label);
            }
        }

        if (dayTwoText != null && dayOneText != null) {
            dayTwoInput = Double.parseDouble(dayTwoText.getText().toString());

            if (dayTwoInput == 0) {
                showErrorMessage(R.string.day_two_error_label);
            } else {
                String percentage = Double.toString(weightChangePercentage(dayOneInput, dayTwoInput));
                //dayTwoPercentLabel.setText(dayTwoPercentLabel.getText() + percentage + "%");
                dayTwoPercentLabel.setText("Day 1 - Day 2 Weight Change: " + percentage + "%");
                dayTwoPercentLabel.setVisibility(TextView.VISIBLE);
            }
        }

        if (dayNText != null && dayTwoText != null) {
            dayNInput = Double.parseDouble(dayNText.getText().toString());

            if (dayNInput <= 0) {
                showErrorMessage(R.string.day_n_error_label);
            } else {
                String percentage = Double.toString(weightChangePercentage(dayTwoInput, dayNInput));
                //dayNPercentLabel.setText(dayNPercentLabel.getText() + percentage + "%");
                dayNPercentLabel.setText("Day 2 - Day n Weight Change: " + percentage + "%");
                dayNPercentLabel.setVisibility(TextView.VISIBLE);
            }
        }
        //to do: add birth - current weight %
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
