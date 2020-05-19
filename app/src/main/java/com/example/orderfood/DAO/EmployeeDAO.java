package com.example.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.database.Database;
import com.example.orderfood.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    SQLiteDatabase database;

    public EmployeeDAO(Context context){
        Database myDatabase = new Database(context);
        database = myDatabase.open();
    }

    public Employee getEmployeeWithID(int idEmploy){
        Employee dto = new Employee();
        String query = "Select * from " + Database.TB_EMPLOYEES + " where " + Database.TB_EMPLOYEE_ID
                + " = '" + idEmploy + "'";
        Cursor cursor = database.rawQuery(query,null);

        if(cursor != null){
            cursor.moveToFirst();
            dto.setId(cursor.getInt(cursor.getColumnIndex(Database.TB_EMPLOYEE_ID)));
            dto.setUsername(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_USERNAME)));
            dto.setPassword(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_PASSWORD)));
            dto.setGenre(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_GENRE)));
            dto.setBirthday(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_BIRTHDAY)));
            dto.setPhone(cursor.getInt(cursor.getColumnIndex(Database.TB_EMPLOYEE_PHONE)));
            dto.setIdRule(cursor.getInt(cursor.getColumnIndex(Database.TB_EMPLOYEE_RULEID)));
            return dto;
        } else {
            return null;
        }
    }

    public int checkEmployees(String username, String password){
        String query = "Select * from " + Database.TB_EMPLOYEES + " where " + Database.TB_EMPLOYEE_USERNAME
                + " = '" + username + "' and " + Database.TB_EMPLOYEE_PASSWORD + " = '" + password + "'";
        int idEmploy = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            idEmploy = cursor.getInt(0);
            cursor.moveToNext();
        }
        return idEmploy;
    }

    public List<Employee> getAllList(){
        List<Employee> list = new ArrayList<Employee>();
        String query = "Select * from " + Database.TB_EMPLOYEES;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Employee dto = new Employee();
            dto.setId(cursor.getInt(cursor.getColumnIndex(Database.TB_EMPLOYEE_ID)));
            dto.setUsername(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_USERNAME)));
            dto.setPassword(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_PASSWORD)));
            dto.setGenre(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_GENRE)));
            dto.setBirthday(cursor.getString(cursor.getColumnIndex(Database.TB_EMPLOYEE_BIRTHDAY)));
            dto.setPhone(cursor.getInt(cursor.getColumnIndex(Database.TB_EMPLOYEE_PHONE)));
            dto.setIdRule(cursor.getInt(cursor.getColumnIndex(Database.TB_EMPLOYEE_RULEID)));
            list.add(dto);
            cursor.moveToNext();
        }
        return list;
    }

    public long insert(Employee employeeDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TB_EMPLOYEE_USERNAME, employeeDTO.getUsername());
        contentValues.put(Database.TB_EMPLOYEE_PASSWORD, employeeDTO.getPassword());
        contentValues.put(Database.TB_EMPLOYEE_GENRE, employeeDTO.getGenre());
        contentValues.put(Database.TB_EMPLOYEE_BIRTHDAY, employeeDTO.getBirthday());
        contentValues.put(Database.TB_EMPLOYEE_PHONE, employeeDTO.getPhone());
        contentValues.put(Database.TB_EMPLOYEE_RULEID,employeeDTO.getIdRule());

        long result = database.insert(Database.TB_EMPLOYEES,null,contentValues);
        return result;
    }

    public boolean update(Employee employeeDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TB_EMPLOYEE_USERNAME,employeeDTO.getUsername());
        contentValues.put(Database.TB_EMPLOYEE_PASSWORD,employeeDTO.getPassword());
        contentValues.put(Database.TB_EMPLOYEE_GENRE,employeeDTO.getGenre());
        contentValues.put(Database.TB_EMPLOYEE_BIRTHDAY,employeeDTO.getBirthday());
        contentValues.put(Database.TB_EMPLOYEE_PHONE,employeeDTO.getPhone());
        contentValues.put(Database.TB_EMPLOYEE_RULEID,employeeDTO.getIdRule());

        long rs = database.update(Database.TB_EMPLOYEES,contentValues,Database.TB_EMPLOYEE_ID + " = " + employeeDTO.getId(),null);
        if(rs != 0)
            return true;
        return false;
    }

    public boolean delete(int idEmploy){
        long rs = database.delete(Database.TB_EMPLOYEES,Database.TB_EMPLOYEE_ID + " = " + idEmploy,null);
        if (rs != 0)
            return true;
        return false;
    }

    public int getRule(int idEmploy){
        String query = "Select * from " + Database.TB_EMPLOYEES + " where " + Database.TB_EMPLOYEE_ID
                + " = '" + idEmploy + "'";
        int idRule = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            idRule = cursor.getInt(cursor.getColumnIndex(Database.TB_EMPLOYEE_RULEID));
            cursor.moveToNext();
        }
        return idRule;
    }
}
