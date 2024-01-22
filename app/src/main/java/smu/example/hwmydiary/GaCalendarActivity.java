package smu.example.hwmydiary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GaCalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calendar_layout);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i + "/" + (i1+1) + "/" + i2;
                Log.d(TAG, "onSelectDayChange: date : "+date);

                Intent intent = new Intent(GaCalendarActivity.this, GaMainActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}