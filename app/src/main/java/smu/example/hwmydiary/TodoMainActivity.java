package smu.example.hwmydiary;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TodoMainActivity extends AppCompatActivity {
    private static final String TAG = "TodoMainActivity";

    TodoMainFragment mainFragment;
    EditText inputToDo;
    Context context;

    public static TodoNoteDatabase noteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todoactivity_main);

        mainFragment = new TodoMainFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveToDo();
                Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();

            }
        });
        openDatabase();
    }
    private void saveToDo(){
        inputToDo = findViewById(R.id.inputToDo);

        // EditText에 적힌 글 가져오기
        String todo = inputToDo.getText().toString();

        // 테이블에 값을 추가하는 sql구문 insert
        String sqlSave = "insert into " + TodoNoteDatabase.TABLE_NOTE + " (TODO) values (" +
                "'" + todo + "')";

        // sql문 실행
        TodoNoteDatabase database = TodoNoteDatabase.getInstance(context);
        database.execSQL(sqlSave);

        // 저장과 동시에 EditText 안의 글 초기화
        inputToDo.setText("");
    }


    public void openDatabase() {
        // 데이터베이스 열기
        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
        noteDatabase = TodoNoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }
}