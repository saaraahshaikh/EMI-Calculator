package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {

    private EditText etPrincipalAmount;
    private EditText etInterest;
    private EditText etMonths;
    private Button btCalc;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onClick();
    }

    private void onClick() {

        btCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Button is Clicked!!",Toast.LENGTH_LONG).show();
                double emiResult = calculateEMI();
                displayResult(emiResult);
            }
        });
    }

    private void findViews() {
        etPrincipalAmount = findViewById(R.id.et_principalAmount);
        etInterest = findViewById(R.id.et_interest);
        etMonths = findViewById(R.id.et_months);
        btCalc = findViewById(R.id.bt_calculate);
        tvResult = findViewById(R.id.tv_result);
    }

    private double calculateEMI() {
        String principalAmountText = etPrincipalAmount.getText().toString();
        String interestText = etInterest.getText().toString();
        String monthsText = etMonths.getText().toString();

        int principalAmountP = Integer.parseInt(principalAmountText);
        double rateOfInterestR = Double.parseDouble(interestText);
        int numberMonthsN = Integer.parseInt(monthsText);

        double totalInterestR = rateOfInterestR*0.00083333;

        double p = principalAmountP*totalInterestR;

        double r = pow(1 + totalInterestR,numberMonthsN);

        double n = r/(r - 1);

        double e = p * n;

        return e;

    }

    private void displayResult(double emi) {
        DecimalFormat dm = new DecimalFormat("0.00");
        String emiResult = dm.format(emi);

        String fullResult;

        fullResult = "Rs. " + emiResult + "!! Here's your EMI!!";

        tvResult.setText(fullResult);
    }

}
