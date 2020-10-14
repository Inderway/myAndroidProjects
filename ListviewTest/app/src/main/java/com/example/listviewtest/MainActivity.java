package com.example.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private String[] data={"王成","李春霖","李志豪","李鑫","刘贵一",
//            "陈卓","刘春雨","张泽宇","方骏","陈振涛","胡世杰","杨彬",
//            "王振宇","管政","殷炜","蒋其越","付佳正","郭昊南","李文炜",
//            "夏洵","滕逸青","韦一宸","黄英杰","陈启航","陆逸凡","孙天琪",
//            "杜琳",};

    private List<Student>studentlist=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStudents();
        StudentAdapter adapter=new StudentAdapter(MainActivity.this,
                R.layout.student_item,studentlist);
//1        //use adapter to pass data
//        //simple_list_item_1 is the id of ListView's child layout, which is a
//        //internal layout file having a TextView
//        ArrayAdapter<String>adapter=new ArrayAdapter<String>(
//                MainActivity.this,android.R.layout.simple_list_item_1,data);


        ListView listView=(ListView)findViewById(R.id.list_view);
        //pass the adapter
        listView.setAdapter(adapter);
        //click event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent,View view,int position,long id){
                Student student=studentlist.get(position);
                Toast.makeText(MainActivity.this, student.getName(),Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initStudents(){
        for(int i=0;i<3;i++){
            Student a=new Student("Wang Cheng",R.drawable.img_1);
            studentlist.add(a);
            Student b=new Student("Li Chunlin",R.drawable.img_1);
            studentlist.add(b);
            Student c=new Student("li Zhihao",R.drawable.img_1);
            studentlist.add(c);
            Student d=new Student("Li Xin",R.drawable.img_1);
            studentlist.add(d);
            Student e=new Student("Liu Guiyi",R.drawable.img_1);
            studentlist.add(e);
        }
    }


    public class Student {
        private String name;
        private int imageId;

        public Student(String name,int imageId){
            this.name=name;
            this.imageId=imageId;
        }

        public String getName() {
            return name;
        }

        public int getImageId() {
            return imageId;
        }
    }


    public class StudentAdapter extends ArrayAdapter<Student>{
        private int resourceId;

        public StudentAdapter(Context context, int textViewResourceId, List<Student> objects) {
            super(context, textViewResourceId,objects);
            resourceId = textViewResourceId;
        }

        class ViewHolder{
            ImageView studentImage;
            TextView studentName;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Student student=getItem(position);//get current Student instance
            //load the layout passed
            //false means that we make layout attributions defined in parent layout work but
            //do not add a parent layout to this View
//1            View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//2            //use cache(convertView) to optimize 1
//            View view;
//            if(convertView==null){
//                view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//            }else{
//                view=convertView;
//            }

            //optimize deeply
            View view;
            ViewHolder viewHolder;
            if(convertView==null){
                view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.studentImage=(ImageView)view.findViewById(R.id.student_image);
                viewHolder.studentName=(TextView)view.findViewById(R.id.student_name);
                view.setTag(viewHolder);//save viewholder in view
            }else{
                view=convertView;
                viewHolder=(ViewHolder)view.getTag();//get viewholder again
            }
            viewHolder.studentImage.setImageResource(student.getImageId());
            viewHolder.studentName.setText(student.getName());
//2            ImageView studentImage=(ImageView) view.findViewById(R.id.student_image);
//            TextView studentName=(TextView)view.findViewById(R.id.student_name);
//            studentImage.setImageResource(student.getImageId());
//            studentName.setText(student.getName());
            return view;
        }
    }


}



