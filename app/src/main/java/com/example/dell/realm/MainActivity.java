package com.example.dell.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {


    EditText name,email;
    TextView show;
    Button save;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        show=findViewById(R.id.tvShow);
        save=findViewById(R.id.btnSave);


        realm=Realm.getDefaultInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                Student student=realm.createObject(Student.class);
                student.setName(name.getText().toString());
                student.setEmail(email.getText().toString());

                realm.commitTransaction();
                showResult();
            }
        });
    }
    public void showResult(){
        RealmResults<Student> student=realm.where(Student.class).findAll();
        String result="";
        for (Student sts:student) {
            result+=sts.getName()+"\n"+sts.getEmail()+"\n";
        }
        show.setText(result);
    }
    //Delete all data in Realm
    public void delete(){
       RealmResults<Student> student=realm.where(Student.class).findAll();
       student.deleteAllFromRealm();
    }
}
