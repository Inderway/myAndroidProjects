package com.example.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button button2=(Button)findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //pass data to last activity before
                Intent intent=new Intent();
                intent.putExtra("data_return","Hello FirstActivity");
                //This method is used to pass data to last activity
                //The first parameter is handle result
                setResult(RESULT_OK,intent);
                finish();
            }
        });

//7        //get Intent that used to launch SecondActivity.
//        Intent intent=getIntent();
//        String data=intent.getStringExtra("extra_data");
//        Log.d("SecondActivity",data);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("data_return","Hello FirstActivity back");
        setResult(RESULT_OK,intent);
        finish();
    }
}
