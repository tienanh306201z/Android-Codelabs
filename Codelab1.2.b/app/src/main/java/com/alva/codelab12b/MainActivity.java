package com.alva.codelab12b;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public final String LOG_TAG = MainActivity.class.getSimpleName();
    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);
        Log.i(LOG_TAG, "Question 1: app:layout_constraintTop_toBottomOf=\"@+id/button_toast\" \n app:layout_constraintBottom_toTopOf=\"@+id/button_count\"");
        Log.i(LOG_TAG, "Question 2: app:layout_constraintLeft_toLeftOf=\"parent\"");
        Log.i(LOG_TAG, "Question 3: public void callMethod(View view)");
        Log.i(LOG_TAG, "Question 4: view.setBackgroundColor()");
    }

    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT).show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
    }

    public void changeToZero(View view) {
        mCount = 0;
        mShowCount.setText(Integer.toString(mCount));
    }
}