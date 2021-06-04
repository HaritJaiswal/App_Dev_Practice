package com.example.databasetrialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass , update_old, update_new, delete;
    MyDbAdapter helper;
    private static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView email_problem;
    TextView password_problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        update_old = (EditText) findViewById(R.id.editText3);
        update_new = (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);
        email_problem = (TextView) findViewById(R.id.email_validity);
        password_problem = (TextView) findViewById(R.id.password_validity);

        email_problem.setVisibility(View.GONE);
        password_problem.setVisibility(View.GONE);

        helper = new MyDbAdapter(this);
    }

    public boolean isEmailValid(String emailToText){
        if (emailToText.isEmpty() || !emailToText.trim().matches(emailPattern)) {
            email_problem.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            email_problem.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean isPasswordValid(String pass) {
        if(!pass.isEmpty() && pass.length()>5){
            password_problem.setVisibility(View.GONE);
            return true;
        }
        password_problem.setVisibility(View.VISIBLE);
        return false;
    }

    public void addUser(View view) {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty()) {
            ToastMessage.message(getApplicationContext(),"Enter Both Name and Password");
        }
        else if (isEmailValid(t1) && isPasswordValid(t2)){
            long id = helper.insertData(t1,t2);
            if(id<=0) {
                ToastMessage.message(getApplicationContext(),"Insertion Unsuccessful");
                Name.setText("");
                Pass.setText("");
            }
            else {
                ToastMessage.message(getApplicationContext(),"Insertion Successful");
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void viewData(View view) {
        String data = helper.getData();
        ToastMessage.message(this,data);
    }

    public void update( View view) {
        String u1 = update_old.getText().toString();
        String u2 = update_new.getText().toString();
        if(u1.isEmpty() || u2.isEmpty()) {
            ToastMessage.message(getApplicationContext(),"Enter Data");
        }
        else {
            int a= helper.updateName( u1, u2);
            if(a<=0) {
                ToastMessage.message(getApplicationContext(),"Unsuccessful");
                update_old.setText("");
                update_new.setText("");
            }
            else {
                ToastMessage.message(getApplicationContext(),"Updated");
                update_old.setText("");
                update_new.setText("");
            }
        }

    }
    public void delete( View view) {
        String uname = delete.getText().toString();
        if(uname.isEmpty()) {
            ToastMessage.message(getApplicationContext(),"Enter Data");
        }
        else{
            int a= helper.delete(uname);
            if(a<=0) {
                ToastMessage.message(getApplicationContext(),"Unsuccessful");
                delete.setText("");
            }
            else {
                ToastMessage.message(this, "DELETED");
                delete.setText("");
            }
        }
    }

    @Override
    protected void onDestroy() {
        helper.destroyDB();
        super.onDestroy();
    }
}