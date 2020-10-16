package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,2);
        Button createDatabase=(Button) findViewById(R.id.create_database);
        Button addData=(Button)findViewById(R.id.add_data);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //put first record
                values.put("name","Success of Yin Wei");
                values.put("author","Yin Wei");
                values.put("pages",101);
                values.put("price",221);
                //The second parameter means that it will assign null to columns
                //we haven't put data by default
                db.insert("Book",null,values);
                values.clear();
                //second record
                values.put("name","Disappearance of Senbai");
                values.put("author","Beast Senbai");
                values.put("pages",114);
                values.put("price",514);
                db.insert("Book",null,values);
                Toast.makeText(MainActivity.this,"insert",Toast.LENGTH_SHORT).show();
            }
        });
        Button updateData=(Button)findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price",10.99);
                //table name, ContentValues; the third and fourth parameter restrain the tuple
                //? means a placeholder, which can be filled by the fourth parameter that is
                //a string array
                db.update("Book",values,"name = ?",new String[]{"Success of Yin Wei"});
            }
        });

        Button deleteButton=(Button)findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.delete("Book","pages > ?",new String[]{"110"});

            }
        });

        Button queryButton=(Button)findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                //query records in Book
                Cursor cursor=db.query("Book",new String[]{"author"},"pages > 110",
                       null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        //traverse all cursor objects
                        //String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        //int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        //double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        //Log.d("MainActivity",name);
                        Log.d("MainActivity",author);
                        //Log.d("MainActivity",pages+"");
                        //Log.d("MainActivity",price+"");
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
