package smu.example.hwmydiary;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment2";

    Context context;
    PieChart chart;
    LineChart chart2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (context != null) {
            context = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);
        initUI(rootView);
        loadStatData();
        return rootView;
    }


    private void initUI(ViewGroup rootView) {
        // 첫번째 그래프
        chart = rootView.findViewById(R.id.chart1);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        chart.setCenterText(getResources().getString(R.string.graph1_title));
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);
        chart.setDrawCenterText(true);
        chart.setHighlightPerTapEnabled(true);

        Legend legend1 = chart.getLegend();
        legend1.setEnabled(false);

        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(10f);

        // 두번째 그래프
        chart2 = rootView.findViewById(R.id.chart2);

        chart2.getDescription().setEnabled(false);
        chart2.setDrawGridBackground(false);
        chart2.setBackgroundColor(Color.WHITE);
        chart2.setViewPortOffsets(0, 0, 0, 0);

        Legend legend2 = chart2.getLegend();
        legend2.setEnabled(false);

        XAxis xAxis2 = chart2.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis2.setTextSize(10f);
        xAxis2.setTextColor(Color.WHITE);
        xAxis2.setDrawAxisLine(false);
        xAxis2.setDrawGridLines(true);
        xAxis2.setTextColor(Color.rgb(255, 192, 56));
        xAxis2.setCenterAxisLabels(true);
        xAxis2.setGranularity(1f);
        xAxis2.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("MM-dd", Locale.KOREA);

            @Override
            public String getFormattedValue(float value) {
                Date date = new Date();
                long millis = date.getTime() + TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date(millis));
            }
        });

        YAxis leftAxis2 = chart2.getAxisLeft();
        leftAxis2.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis2.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis2.setDrawGridLines(true);
        leftAxis2.setGranularityEnabled(true);
        leftAxis2.setAxisMinimum(0f);
        leftAxis2.setAxisMaximum(5f);
        leftAxis2.setYOffset(-9f);
        leftAxis2.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis2 = chart2.getAxisRight();
        rightAxis2.setEnabled(false);

    }

    private void setData1(HashMap<String,Integer> dataHash1) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        String[] keys = {"0", "1", "2", "3", "4"};
        int[] icons = {R.drawable.songre1, R.drawable.songre2,
                R.drawable.songre3, R.drawable.songre4,
                R.drawable.songre5};
        for (int i = 0; i < keys.length; i++) {
            int value = 0;
            Integer outValue = dataHash1.get(keys[i]);
            if (outValue != null) {
                value = outValue.intValue();
            }
            if (value > 0) {
                entries.add(new PieEntry(value, "",
                        getResources().getDrawable(icons[i])));
            }
        }
        PieDataSet dataSet = new PieDataSet(entries, getResources().getString(R.string.graph1_title));
        dataSet.setDrawIcons(true);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, -40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(22.0f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);
        chart.invalidate();
    }

    private void setData2(ArrayList<Float> dataKeys2, ArrayList<Integer> dataValues2) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataKeys2.size(); i++) {
            try {
                float outKey = dataKeys2.get(i);
                Integer outValue = dataValues2.get(i);
                AppConstants.println("#" + i + " -> " + outKey + ", " + outValue);
                entries.add(new Entry(outKey, new Float(outValue)));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        LineDataSet set1 = new LineDataSet(entries, "기분 변화");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1.5f);
        set1.setDrawCircles(true);
        set1.setDrawValues(false);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);

        LineData data = new LineData(set1);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);

        chart2.setData(data);
        chart2.invalidate();
    }

    public void loadStatData() {
        NoteDatabase database = NoteDatabase.getInstance(context);

        // 첫번째 그래프
        String sql = "select mood " +
                "  , count(mood) " +
                "from " + NoteDatabase.TABLE_NOTE + " " +
                "where create_date > '" + getMonthBefore(1) + "' " +
                "  and create_date < '" + getTomorrow() + "' " +
                "group by mood";
        Cursor cursor = database.rawQuery(sql);
        int recordCount = cursor.getCount();
        AppConstants.println("recordCount : " + recordCount);

        HashMap<String,Integer> dataHash1 = new HashMap<String,Integer>();
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            String moodName = cursor.getString(0);
            int moodCount = cursor.getInt(1);

            AppConstants.println("#" + i + " -> " + moodName + ", " + moodCount);
            dataHash1.put(moodName, moodCount);
        }
        setData1(dataHash1);

        // 두번째 그래프
        sql = "select strftime('%Y-%m-%d', create_date) " +
                "  , avg(cast(mood as real)) " +
                "from " + NoteDatabase.TABLE_NOTE + " " +
                "where create_date > '" + getDayBefore(7) + "' " +
                "  and create_date < '" + getTomorrow() + "' " +
                "group by strftime('%Y-%m-%d', create_date)";
        cursor = database.rawQuery(sql);
        recordCount = cursor.getCount();
        AppConstants.println("recordCount : " + recordCount);

        HashMap<String,Integer> recordsHash = new HashMap<String,Integer>();
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            String monthDate = cursor.getString(0);
            int moodCount = cursor.getInt(1);

            AppConstants.println("#" + i + " -> " + monthDate + ", " + moodCount);
            recordsHash.put(monthDate, moodCount);
        }

        ArrayList<Float> dataKeys2 = new ArrayList<Float>();
        ArrayList<Integer> dataValues2 = new ArrayList<Integer>();

        Date todayDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayDate);
        cal.add(Calendar.DAY_OF_MONTH, -7);

        for (int i = 0; i < 7; i++) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            String monthDate = AppConstants.dateFormat5.format(cal.getTime());
            Object moodCount = recordsHash.get(monthDate);

            dataKeys2.add((i-6) * 24.0f);
            if (moodCount == null) {
                dataValues2.add(0);
            } else {
                dataValues2.add((Integer)moodCount);
            }
            AppConstants.println("#" + i + " -> " + monthDate + ", " + moodCount);
        }
        setData2(dataKeys2, dataValues2);

    }

    public String getToday() {
        Date todayDate = new Date();
        return AppConstants.dateFormat5.format(todayDate);
    }

    public String getTomorrow() {
        Date todayDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayDate);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return AppConstants.dateFormat5.format(cal.getTime());
    }

    public String getDayBefore(int amount) {
        Date todayDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayDate);
        cal.add(Calendar.DAY_OF_MONTH, (amount * -1));
        return AppConstants.dateFormat5.format(cal.getTime());
    }

    public String getMonthBefore(int amount) {
        Date todayDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayDate);
        cal.add(Calendar.MONTH, (amount * -1));
        return AppConstants.dateFormat5.format(cal.getTime());
    }

}