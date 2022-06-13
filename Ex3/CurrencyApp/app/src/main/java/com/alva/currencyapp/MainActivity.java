package com.alva.currencyapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.alva.currencyapp.models.Currency;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    AppCompatButton buttonCE;
    AppCompatButton buttonClear;
    AppCompatButton button1;
    AppCompatButton button2;
    AppCompatButton button3;
    AppCompatButton button4;
    AppCompatButton button5;
    AppCompatButton button6;
    AppCompatButton button7;
    AppCompatButton button8;
    AppCompatButton button9;
    AppCompatButton button0;
    AppCompatButton buttonDot;

    AppCompatTextView moneySign1;
    AppCompatTextView moneySign2;
    AppCompatTextView cash1;
    AppCompatTextView cash2;

    Spinner spin1;
    Spinner spin2;

    AppCompatTextView exchangeValue;
    ArrayList<Currency> currencies = new ArrayList<>(Arrays.asList(new Currency("United States - Dollar", "USD", "$", 1), new Currency("Vietnam - Dong", "VND", "đ", 23185), new Currency("Euro - Euro", "EUR", "€", 0.96), new Currency("Japan - Yen", "JPY", "¥", 133.86), new Currency("KOREA - Won", "KRW", "₩", 1293.12)));
    List<String> currency = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currency = currencies.stream().map(Currency::getTitle).collect(Collectors.toList());
        setContentView(R.layout.activity_main);

        initialization();
        setCLick();
        appBarSetUp();
        spinnerSetUp();

    }

    public void appBarSetUp() {
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "Currency" + "</font>"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.actionbar_space_between_icon_and_title);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E0E8EB")));
    }

    public void initialization() {
        buttonCE = findViewById(R.id.button_CE);
        buttonClear = findViewById(R.id.button_clear);
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonDot = findViewById(R.id.button_dot);

        moneySign1 = findViewById(R.id.currency_1);
        moneySign2 = findViewById(R.id.currency_2);

        cash1 = findViewById(R.id.cash_1);
        cash2 = findViewById(R.id.cash_2);

        spin1 = findViewById(R.id.spinner1);
        spin2 = findViewById(R.id.spinner2);

        exchangeValue = findViewById(R.id.exchange_value);
    }

    public void setCLick() {
        buttonCE.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
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
        buttonDot.setOnClickListener(this);
    }

    public void spinnerSetUp() {
        spin1.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currency);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currency);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spin1.setAdapter(adapter1);
        spin2.setAdapter(adapter2);
    }

    public void changeText(Button button) {
        if (cash1.getText() == null || cash1.getText().toString().equals("0")) {
            cash1.setText(button.getText().toString());
        } else {
            cash1.setText(cash1.getText().toString() + button.getText().toString());
        }
    }

    public void clearAll() {
        cash1.setText("0");
    }

    public void clearOneChar() {
        if (!cash1.getText().toString().equals("0") && cash1.getText().toString().length() != 1) {
            cash1.setText(cash1.getText().toString().substring(0, cash1.getText().toString().length() - 1));
        } else if (cash1.getText().toString().length() == 1) {
            cash1.setText("0");
        }
    }

    public void addDot() {
        if (!cash1.getText().toString().contains(".")) {
            cash1.setText(cash1.getText().toString() + ".");
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        setExchangeValue();
        calculate();
        changeMoneySign();
    }

    void changeMoneySign() {
        Currency currency1 = currencies.stream().filter(item -> item.getTitle().equals(spin1.getSelectedItem().toString())).findFirst().get();
        Currency currency2 = currencies.stream().filter(item -> item.getTitle().equals(spin2.getSelectedItem().toString())).findFirst().get();

        moneySign1.setText(currency1.getMoneySign());
        moneySign2.setText(currency2.getMoneySign());
    }

    void setExchangeValue() {
        Currency currency1 = currencies.stream().filter(item -> item.getTitle().equals(spin1.getSelectedItem().toString())).findFirst().get();
        Currency currency2 = currencies.stream().filter(item -> item.getTitle().equals(spin2.getSelectedItem().toString())).findFirst().get();

        exchangeValue.setText("1 " + currency1.getMoneyStandFor() + " = " + new DecimalFormat(".##").format(currency2.getExchangeRate() * 1.0 / currency1.getExchangeRate()) + " " + currency2.getMoneyStandFor());
    }

    void calculate() {
        Currency currency1 = currencies.stream().filter(item -> item.getTitle().equals(spin1.getSelectedItem().toString())).findFirst().get();
        Currency currency2 = currencies.stream().filter(item -> item.getTitle().equals(spin2.getSelectedItem().toString())).findFirst().get();

        cash2.setText(new DecimalFormat("#.##").format(Double.parseDouble(cash1.getText().toString()) * (currency2.getExchangeRate() * 1.0 / currency1.getExchangeRate())));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view == buttonCE) {
            clearOneChar();
        } else if (view == buttonClear) {
            clearAll();
        } else if (view == buttonDot) {
            addDot();
        } else changeText((Button) view);

        calculate();
    }
}