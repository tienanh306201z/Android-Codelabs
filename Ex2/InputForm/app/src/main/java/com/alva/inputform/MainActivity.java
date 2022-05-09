package com.alva.inputform;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText studentId;
    private EditText name;
    private EditText id;
    private EditText phone;
    private EditText email;

    private TextView alertStudentId;
    private TextView alertName;
    private TextView alertId;
    private TextView alertPhone;
    private TextView alertEmail;
    private TextView alertCheckbox;

    private CheckBox checkBox;
    private Button submit;

    private TextView dob;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();
    }

    private void initEvent() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert();
                if (canSubmit()) {

                    Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_LONG).show();
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dob.setText(i2 + "/" + (i1 + 1) + "/" + i);
            }
        });
    }

    private void alert() {
        alertStudentId.setVisibility((studentId.getText().length() > 0) ? View.INVISIBLE : View.VISIBLE);
        alertName.setVisibility((name.getText().length() > 0) ? View.INVISIBLE : View.VISIBLE);
        alertId.setVisibility((id.getText().length() > 0) ? View.INVISIBLE : View.VISIBLE);
        alertPhone.setVisibility((phone.getText().length() > 0) ? View.INVISIBLE : View.VISIBLE);
        alertEmail.setVisibility((email.getText().length() > 0) ? View.INVISIBLE : View.VISIBLE);

        alertCheckbox.setVisibility((checkBox.isChecked()) ? View.INVISIBLE : View.VISIBLE);
    }

    private void initView() {
        studentId = findViewById(R.id.student_id);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        checkBox = findViewById(R.id.checkbox);
        submit = findViewById(R.id.submit);

        dob = findViewById(R.id.dob);
        calendarView = findViewById(R.id.calendarView);
        dob.setText("" + new SimpleDateFormat("dd/MM/yyyy").format(new Date(calendarView.getDate())));

        alertStudentId = findViewById(R.id.alert_student_id);
        alertName = findViewById(R.id.alert_name);
        alertId = findViewById(R.id.alert_id);
        alertPhone = findViewById(R.id.alert_phone);
        alertEmail = findViewById(R.id.alert_email);
        alertCheckbox = findViewById(R.id.alert_checkbox);
    }

    private boolean canSubmit() {
        return studentId.getText().length() > 0
                && name.getText().length() > 0
                && id.getText().length() > 0
                && phone.getText().length() > 0
                && email.getText().length() > 0
                && checkBox.isChecked();
    }
}