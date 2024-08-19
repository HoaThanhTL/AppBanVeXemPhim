package com.example.appbanvexemphim.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appbanvexemphim.Adapter.Database.DBHelper;
import com.example.appbanvexemphim.Model.Notify;

import java.util.ArrayList;

public class NotifyDAO extends DBHelper {
    @SuppressLint("StaticFieldLeak")
    private static NotifyDAO instance;
    public static NotifyDAO getInstance(Context context){
        if (instance == null){
            instance = new NotifyDAO(context);
        }
        return instance;
    }
    private SQLiteDatabase db;
    public NotifyDAO(Context context) {
        super(context);
        db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }
    @SuppressLint({"Recycle","Range"})
    public ArrayList<Notify> getByUser(int user_id){
        ArrayList<Notify> notifies = new ArrayList<>();
        String sql = "SELECT * FROM notify WHERE user_id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(user_id)});
        while (c.moveToNext()) {
            Notify notify = new Notify();
            notify.setId(c.getInt(c.getColumnIndex("id")));
            notify.setTitle(c.getString(c.getColumnIndex("title")));
            notify.setContent(c.getString(c.getColumnIndex("content")));
            notify.setUser_id(c.getInt(c.getColumnIndex("user_id")));
            notifies.add(notify);
        }
        return notifies;
    }
}
