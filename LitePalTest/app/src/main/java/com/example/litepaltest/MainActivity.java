package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.tablemanager.Connector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase=(Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
                Toast.makeText(MainActivity.this,"Create Succeeded",Toast.LENGTH_SHORT).show();
            }
        });

        Button adddata=(Button)findViewById(R.id.add_data);
        adddata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Book book=new Book();
                book.setName("刘贵一传");
                book.setAuthor("殷炜");
                book.setPages(514);
                book.setPrice(20.35);
                book.setPress("东南大学出版社");
                //set the data
                book.save();
                Toast.makeText(MainActivity.this,"Add Succeeded",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
