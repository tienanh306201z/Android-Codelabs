package com.alva.calculator;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import javax.script.ScriptEngine;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean dotUsed = false;

    private boolean equalClicked = false;
    private String lastExpression = "";

    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_DOT = 4;

    AppCompatButton button0;
    AppCompatButton button1;
    AppCompatButton button2;
    AppCompatButton button3;
    AppCompatButton button4;
    AppCompatButton button5;
    AppCompatButton button6;
    AppCompatButton button7;
    AppCompatButton button8;
    AppCompatButton button9;

    ScriptEngine scriptEngine;

    AppCompatButton buttonClearAll;
    AppCompatButton buttonClearNumber;
    AppCompatButton buttonBS;
    AppCompatButton buttonDivision;
    AppCompatButton buttonMultiplication;
    AppCompatButton buttonSubtraction;
    AppCompatButton buttonAddition;
    AppCompatButton buttonEqual;
    AppCompatButton buttonDot;
    AppCompatButton buttonSignedNumber;

    AppCompatTextView inputNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
        setOnClickListener();
    }

    private void initializeVariables() {
        button0 = findViewById(R.id.button_zero);
        button1 = findViewById(R.id.button_one);
        button2 = findViewById(R.id.button_two);
        button3 = findViewById(R.id.button_three);
        button4 = findViewById(R.id.button_four);
        button5 = findViewById(R.id.button_five);
        button6 = findViewById(R.id.button_six);
        button7 = findViewById(R.id.button_seven);
        button8 = findViewById(R.id.button_eight);
        button9 = findViewById(R.id.button_nine);

        buttonClearAll = findViewById(R.id.button_clear_all);
        buttonClearNumber = findViewById(R.id.button_clear_number);
        buttonBS = findViewById(R.id.button_BS);
        buttonDivision = findViewById(R.id.button_division);
        buttonMultiplication = findViewById(R.id.button_multiplication);
        buttonSubtraction = findViewById(R.id.button_subtraction);
        buttonAddition = findViewById(R.id.button_addition);
        buttonEqual = findViewById(R.id.button_equal);
        buttonDot = findViewById(R.id.button_dot);
        buttonSignedNumber = findViewById(R.id.button_signed_number);

        inputNumbers = findViewById(R.id.input_numbers);
    }

    private void setOnClickListener() {
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        buttonClearAll.setOnClickListener(this);
        buttonClearNumber.setOnClickListener(this);
        buttonBS.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);
        buttonSubtraction.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonSignedNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_zero:
                if (addNumber("0")) equalClicked = false;
                break;
            case R.id.button_one:
                if (addNumber("1")) equalClicked = false;
                break;
            case R.id.button_two:
                if (addNumber("2")) equalClicked = false;
                break;
            case R.id.button_three:
                if (addNumber("3")) equalClicked = false;
                break;
            case R.id.button_four:
                if (addNumber("4")) equalClicked = false;
                break;
            case R.id.button_five:
                if (addNumber("5")) equalClicked = false;
                break;
            case R.id.button_six:
                if (addNumber("6")) equalClicked = false;
                break;
            case R.id.button_seven:
                if (addNumber("7")) equalClicked = false;
                break;
            case R.id.button_eight:
                if (addNumber("8")) equalClicked = false;
                break;
            case R.id.button_nine:
                if (addNumber("9")) equalClicked = false;
                break;
            case R.id.button_addition:
                if (addOperand("+")) equalClicked = false;
                break;
            case R.id.button_subtraction:
                if (addOperand("-")) equalClicked = false;
                break;
            case R.id.button_multiplication:
                if (addOperand("x")) equalClicked = false;
                break;
            case R.id.button_division:
                if (addOperand("/")) equalClicked = false;
                break;
            case R.id.button_dot:
                if (addDot()) equalClicked = false;
                break;
            case R.id.button_clear_all:
                inputNumbers.setText("");
                dotUsed = false;
                equalClicked = false;
                break;
            case R.id.button_equal:
                String result = inputNumbers.getText().toString();
                if (!result.equals(""))
                    calculate(inputNumbers.getText().toString());
                break;
        }
    }

    private boolean addNumber(String number) {
        boolean done = false;
        int operationLength = inputNumbers.getText().length();
        if (operationLength > 0) {
            String lastCharacter = inputNumbers.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0")) {
                inputNumbers.setText(number);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT) {
                inputNumbers.setText(inputNumbers.getText() + number);
                done = true;
            }
        } else {
            inputNumbers.setText(inputNumbers.getText() + number);
            done = true;
        }
        return done;
    }

    private int defineLastCharacter(String lastCharacter) {
        try {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("/")))
            return IS_OPERAND;

        if (lastCharacter.equals("."))
            return IS_DOT;

        return -1;
    }

    private boolean addOperand(String operand) {
        boolean done = false;
        int operationLength = inputNumbers.getText().length();
        if (operationLength > 0) {
            String lastInput = inputNumbers.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("/"))) {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else {
                inputNumbers.setText(inputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addDot() {
        boolean done = false;

        if (inputNumbers.getText().length() == 0) {
            inputNumbers.setText("0.");
            dotUsed = true;
            done = true;
        } else if (dotUsed == true) {
        } else if (defineLastCharacter(inputNumbers.getText().charAt(inputNumbers.getText().length() - 1) + "") == IS_OPERAND) {
            inputNumbers.setText(inputNumbers.getText() + "0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(inputNumbers.getText().charAt(inputNumbers.getText().length() - 1) + "") == IS_NUMBER) {
            inputNumbers.setText(inputNumbers.getText() + ".");
            done = true;
            dotUsed = true;
        }
        return done;
    }

    private void saveLastExpression(String input) {
        String lastOfExpression = input.charAt(input.length() - 1) + "";
        if (input.length() > 1) {
            if (defineLastCharacter(lastOfExpression + "") == IS_NUMBER) {
                lastExpression = lastOfExpression;
                for (int i = input.length() - 2; i >= 0; i--) {
                    String last = input.charAt(i) + "";
                    if (defineLastCharacter(last) == IS_NUMBER || defineLastCharacter(last) == IS_DOT) {
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(last) == IS_OPERAND) {
                        lastExpression = last + lastExpression;
                        break;
                    }
                    if (i == 0) {
                        lastExpression = "";
                    }
                }
            }
        }
    }

    private void calculate(String input) {
        Log.d("hello",input);
        String result = "";
        try {
            result = scriptEngine.eval(input.replaceAll("x", "*")).toString();
            Log.d("hello", input);
            BigDecimal decimal = new BigDecimal(result);
            result = decimal.setScale(8, BigDecimal.ROUND_HALF_UP).toPlainString();
            equalClicked = true;

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Wrong Format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.equals("Infinity")) {
            Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
            inputNumbers.setText(input);

        } else if (result.contains(".")) {
            result = result.replaceAll("\\.?0*$", "");
            inputNumbers.setText(result);
        }
    }
}