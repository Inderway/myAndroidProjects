package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        //toast on button
        Button button1=(Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                //the second parameter is a request code, which should be a unique value
                startActivityForResult(intent,1);

//7                //pass data to next activity
//                String data="Hello SecondActivity";
//                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
//                //the first parameter is a key used to get data.
//                intent.putExtra("extra_data",data);
//                startActivity(intent);

//6                //call out dial interface
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);

//5                Intent intent=new Intent(Intent.ACTION_VIEW);
//                //setData() receives a Uri object, which is used to assign the operating data
//                //for current Intent
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent);

//4                Intent intent=new Intent("com.example.activitytest.ACTION_START");
//                //add a self-defined category
//                intent.addCategory(("com.example.activitytest.MY_CATEGORY"));
//                startActivity(intent);

//3                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
//                startActivity(intent);
                //2 finish();

//1                Toast.makeText(FirstActivity.this,"Button 1 clicked",
//                        Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK){
                    String returnedData=data.getStringExtra("data_return");
                    Log.d("FirstActivity",returnedData);
                }
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"You clicked Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"You clicked Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
