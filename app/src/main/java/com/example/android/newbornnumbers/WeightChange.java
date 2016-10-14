package com.example.android.newbornnumbers;

import android.icu.text.DecimalFormat;
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

        submitButton = (Button)findViewById(R.id.submitWeightButton);

        submitButton.setOnClickListener(this);
    }


    public void onClick(View v) {

        dayOneText = (EditText)findViewById(R.id.dayOneWeightInput);
        dayTwoText = (EditText)findViewById(R.id.dayTwoWeightInput);
        dayNText = (EditText)findViewById(R.id.dayNWeightInput);

        TextView dayOnePercentLabel = (TextView)findViewById(R.id.dayOneWeightChangeLabel);
        TextView dayTwoPercentLabel = (TextView)findViewById(R.id.dayTwoWeightChangeLabel);
        TextView dayNPercentLabel = (TextView)findViewById(R.id.dayNWeightChangeLabel);

        double dayOneInput = 0;
        double dayTwoInput = 0;
        double dayNInput = 0;

        if (dayOneText != null) {
            dayOneInput = Double.parseDouble(dayOneText.getText().toString());
            //to do: grab the birth weight that was entered in initial input
        }

        if (dayTwoText != null && dayOneText != null) {
            dayTwoInput = Double.parseDouble(dayTwoText.getText().toString());

            if (dayTwoInput == 0){
                //to do: throw error here -- "Please check the value of Day 2 weight"
            }
            else{
                String percentage = Double.toString(weightChangePercentage(dayOneInput, dayTwoInput));
                //dayTwoPercentLabel.setText(dayTwoPercentLabel.getText() + percentage + "%");
                dayTwoPercentLabel.setText("Day 1 - Day 2 Weight Change: " + percentage + "%");
                dayTwoPercentLabel.setVisibility(TextView.VISIBLE);
            }
        }

        if (dayNText != null && dayTwoText != null){
            dayNInput = Double.parseDouble(dayNText.getText().toString());

            if (dayNInput == 0){
                //to do: throw error here -- "Please check the value of Day N weight"
            }
            else{
                String percentage = Double.toString(weightChangePercentage(dayTwoInput, dayNInput));
                //dayNPercentLabel.setText(dayNPercentLabel.getText() + percentage + "%");
                dayNPercentLabel.setText("Day 2 - Day n Weight Change: " + percentage + "%");
                dayNPercentLabel.setVisibility(TextView.VISIBLE);
            }
        }

        //to do: add birth - current weight %
    }

    protected double weightChangePercentage(double startingWeight, double endingWeight){

        double weightChange = Math.round(endingWeight / startingWeight * 100);
        return weightChange;
    }

}

