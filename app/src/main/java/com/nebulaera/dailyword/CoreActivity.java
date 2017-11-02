package com.nebulaera.dailyword;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.nebulaera.dailyword.constants.SQLiteTableConstants;
import com.nebulaera.dailyword.data.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CoreActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String LYJ_TAG = "LYJ_CoreActivity";
    private ListView contentsLv;
    private Button addBtn;
    private EditText inputEt;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private List<String> items = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        initOperations();
        initViews();
        setListens();
        setAdapter();
    }

    private void setAdapter() {
        items.clear();
        query(items);
        contentsLv.setAdapter(arrayAdapter);
        contentsLv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        contentsLv.setStackFromBottom(true);
    }

    private void query(List<String> items) {
        Cursor cursor = sqLiteDatabase.query(SQLiteTableConstants.TABLE_DAILY, new String[]{SQLiteTableConstants.DAILY_CONTENT}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(SQLiteTableConstants.DAILY_CONTENT);
            Log.v(LYJ_TAG, "[columnIndex:{" + columnIndex + "}]");
            String content = cursor.getString(columnIndex);
            items.add(content);
        }
        cursor.close();
    }

    private void initOperations() {
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this, "daily", null, 1);
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
    }

    private void initViews() {
        contentsLv = (ListView) findViewById(R.id.lv_contents);
        addBtn = (Button) findViewById(R.id.btn_add);
        inputEt = (EditText) findViewById(R.id.et_input_content);
    }

    private void setListens() {
        addBtn.setOnClickListener(this);
        contentsLv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                insertData();
                updateList();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * 更新ListView列表
     */
    private void updateList() {

    }

    private void query() {

    }

    /**
     * 向数据库插入数据
     */
    private void insertData() {
        String content = inputEt.getText().toString().trim();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteTableConstants.DAILY_CONTENT, content);
        long insert = sqLiteDatabase.insert(SQLiteTableConstants.TABLE_DAILY, null, contentValues);
        Log.v(LYJ_TAG, "[insert id:{" + insert + "}]");
        setAdapter();
    }

    private void sendNotify() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);
        remoteViews.setImageViewResource(R.id.iv_icon, R.mipmap.ic_launcher_round);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(10, pendingIntent);

        Notification build = builder.setSmallIcon(R.mipmap.ic_launcher_round).setCustomContentView(remoteViews).setContentIntent(pendingIntent).build();
        build.flags = Notification.FLAG_ONGOING_EVENT;
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(9, build);
    }
}
