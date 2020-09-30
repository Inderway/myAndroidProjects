package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Task id is "+ getTaskId());
        setContentView(R.layout.second_layout);
        Button button2=(Button)findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                //if the mode of FirstActivity is singleTop, it will be created again
                //The recent created FirstActivity is different from the first one.
                startActivity(intent);

//                //pass data to last activity before
//                Intent intent=new Intent();
//                intent.putExtra("data_return","Hello FirstActivity");
//                //This method is used to pass data to last activity
//                //The first parameter is handle result
//                setResult(RESULT_OK,intent);
//                finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
    }

    //Use this method to pass data that SecondActivity needs
    public  static void actionStart(Context context, String data1, String data2){
        Intent intent=new Intent(context,SecondActivity.class);
        intent.putExtra("param1","data1");
        intent.putExtra("param2","data2");
        context.startActivity(intent);
    }

}
