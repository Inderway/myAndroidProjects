package com.example.uibestpractice;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();
        inputText=(EditText)findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    //Refresh the display if have new message
                    adapter.notifyItemInserted(msgList.size()-1);
                    //locate to last line of ListView
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");//clear the content in inputText
                }
            }
        });
    }

    private void initMsgs(){
        Msg msg1=new Msg("Hello!",Msg.TYPE_RECERVED);
        msgList.add(msg1);
        Msg msg2=new Msg("Hello!222222222222222222222222222222222",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("Hello!33333333333333333333333333333",Msg.TYPE_RECERVED);
        msgList.add(msg3);
    }

}
