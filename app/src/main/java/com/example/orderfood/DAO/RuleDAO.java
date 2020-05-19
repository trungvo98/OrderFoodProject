package com.example.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.database.Database;
import com.example.orderfood.models.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleDAO {
    SQLiteDatabase database;

    public RuleDAO(Context context){
        Database myDatabase = new Database(context);
        database = myDatabase.open();
    }

    public List<Rule> getAllList(){
        List<Rule> list = new ArrayList<>();
        String query = "Select * from " + Database.TB_RULES;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Rule dto = new Rule();
            dto.setId(cursor.getInt(cursor.getColumnIndex(Database.TB_RULE_ID)));
            dto.setName(cursor.getString(cursor.getColumnIndex(Database.TB_RULE_NAME)));
            list.add(dto);
            cursor.moveToNext();
        }
        return list;
    }

    public void insert(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TB_RULE_NAME,name);
        database.insert(Database.TB_RULES,null,contentValues);
    }
}
