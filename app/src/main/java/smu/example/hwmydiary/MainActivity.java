package smu.example.hwmydiary;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button      diary, memo, daily, monthly, ga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diary = findViewById(R.id.btn_diary);
        memo = findViewById(R.id.btn_memo);
        daily = findViewById(R.id.btn_daily);
        monthly = findViewById(R.id.btn_monthly);
        ga = findViewById(R.id.btn_ga);

        diary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        memo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemoMainActivity.class);
                startActivity(intent);
            }
        });
        daily.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodoMainActivity.class);
                startActivity(intent);
            }
        });
        monthly.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalMain.class);
                startActivity(intent);
            }
        });
        ga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GaMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
