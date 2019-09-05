// Assignment #:Homework 01
// File Name: MainActivity.java
// Full Name: Maheshwar Anil Mundhe & Jignesh Manoj Jain
// Group #: 10


package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_bmivalue;
    private TextView tv_bmistatus;
    private EditText et_weight;
    private EditText et_height1;
    private EditText et_height2;
    private Button btn_calculate;
    private double weight;
    private double height1;
    private double height2;
    private Toast calculatedtoast;
    private double bmi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Calculator");

        et_weight = findViewById(R.id.et_weight);
        et_height1 = findViewById(R.id.et_height1);
        et_height2 = findViewById(R.id.et_height2);
        tv_bmivalue = findViewById(R.id.tv_bmivalue);
        tv_bmistatus = findViewById(R.id.tv_bmistatus);
        btn_calculate = findViewById(R.id.btn_calculate);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight = 0.0;
                height1 = 0.0;
                height2 = 0.0;

                clearValues();

                String tempWeight = et_weight.getText().toString();
                String tempHeight1 = et_height1.getText().toString();
                String tempHeight2 = et_height2.getText().toString();

                if (tempWeight.equals("")) {
                    et_weight.setError("Please Enter a Value");
                    clearValues();
                }

                if (tempHeight1.equals("")) {
                    et_height1.setError("Please Enter a Value");
                    clearValues();
                }

                if (tempHeight2.equals("")) {
                    et_height2.setError("Please Enter a Value");
                    clearValues();
                }

                if (tempWeight.equals("0")){
                    et_weight.setError("Please Enter a Valid Value");
                    clearValues();
                }

                if (tempHeight1.equals("0")){
                    et_height1.setError("Please Enter a Valid Value");
                    clearValues();
                }

                if (tempHeight2.equals("0")){
                    et_height2.setError("Please Enter a Valid Value");
                    clearValues();
                }

                if (tempWeight != null && !tempWeight.equals("")) {
                    weight = Double.parseDouble(tempWeight);
                }

                if (tempHeight1 != null && !tempHeight1.equals("")) {
                    height1 = Double.parseDouble(tempHeight1);
                }

                if (tempHeight2 != null && !tempHeight2.equals("")) {
                    height2 = Double.parseDouble(tempHeight2);
                }

                if (weight > 0 && height1 > 0 && height2 > 0) {
                    bmi = (703 * weight) / (((height1 * 12) + height2) * ((height1 * 12) + height2));
                }

                if (bmi > 0) {
                    calculatedtoast = Toast.makeText(getApplicationContext(),
                            "BMI Calculated!",
                            Toast.LENGTH_SHORT);

                    if (bmi < 18.5) {
                        tv_bmistatus.setText("You are Underweight!");

                    } else if (bmi > 18.5 && bmi < 24.9) {
                        tv_bmistatus.setText("You have Normal Weight!");

                    } else if (bmi > 25 && bmi < 29.9) {
                        tv_bmistatus.setText("You are Overweight!");

                    } else if (bmi == 30 || bmi > 30) {
                        tv_bmistatus.setText("You are Obese!");
                    }

                    tv_bmivalue.setText((float)Math.round(bmi * 100) / 100 + "");
                    tv_bmivalue.setVisibility(View.VISIBLE);
                    tv_bmistatus.setVisibility(View.VISIBLE);
                    calculatedtoast.show();

                    InputMethodManager inputManager = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
    }

    private void clearValues(){
        tv_bmivalue.setText("");
        tv_bmistatus.setText("");
        tv_bmivalue.setVisibility(View.INVISIBLE);
        tv_bmistatus.setVisibility(View.INVISIBLE);
        bmi = 0.0;
    }
}
