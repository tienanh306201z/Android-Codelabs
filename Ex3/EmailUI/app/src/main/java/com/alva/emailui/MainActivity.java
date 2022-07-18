package com.alva.emailui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alva.emailui.adapters.EmailAdapter;
import com.alva.emailui.models.Email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<Email> data = new ArrayList<>(Arrays.asList(
            new Email("Edurila.com", "$19 On;y (First 10 spots) - Bestselling...", "Are you looking to Learn Web Designin...", "E", "#345cd6", "12:34 PM"),
            new Email("Chris Abad", "Help make Campaign Monitor better", "Let us know your thoughts! No images...", "C", "#d66142", "11:22 AM"),
            new Email("Tuto.com", "8h de formation gratuite et les nouvaea...", "Photoshop, SEO, Blender, CSS, WordPre...", "T", "#7ad65f", "11:04 AM"),
            new Email("support", "Societe Ovh : suivi de vos services - hp...", "SAS OVH - http://www/ovh.com 2 rue K...", "S", "#7a8282", "10:26 AM"),
            new Email("Matt from Ionic", "The New Ionic Creator is Here!", "Announcing the all-new Creator, build...", "M", "#97d67a", "09:34 AM")
    ));

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + "Inbox" + "</font>"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.actionbar_space_between_icon_and_title);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#eb443c")));

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EmailAdapter emailAdapter = new EmailAdapter(this, data);

        recyclerView.setAdapter(emailAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
}