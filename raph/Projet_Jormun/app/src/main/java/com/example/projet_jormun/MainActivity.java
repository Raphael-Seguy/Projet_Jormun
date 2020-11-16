package com.example.projet_jormun;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityEquipement();
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCombat();
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityVillage();
            }
        });
    }
    public void openActivityEquipement() {
        Intent intent = new Intent(this, Activity_Equipement.class);
        startActivity(intent);
    }
    public void openActivityCombat() {
        Intent intent = new Intent(this, Activity_Combat.class);
        startActivity(intent);
    }
    public void openActivityVillage() {
        Intent intent = new Intent(this, Activity_Village.class);
        startActivity(intent);
    }
}
