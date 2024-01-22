package smu.example.hwmydiary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class MyCursorAdapter extends CursorAdapter {
    String TAG = "MyCursorAdapter";
    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    //리스트뷰에 표시될 뷰 반환
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from( context );
        View v = inflater.inflate( R.layout.list_item2, parent,false );
        return v;

    }

    //뷰의 속성 지정
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView item_context = (TextView) view.findViewById( R.id.item_context );
        TextView item_price = (TextView) view.findViewById( R.id.item_price );

        @SuppressLint("Range") String contexts = cursor.getString( cursor.getColumnIndex( GaMainActivity.KEY_CONTEXT ) );
        @SuppressLint("Range") String price = cursor.getString( cursor.getColumnIndex( GaMainActivity.KEY_PRICE ) );

        Log.d(TAG, contexts + ", " + price);

        item_context.setText( contexts );
        item_price.setText( price );
    }
}