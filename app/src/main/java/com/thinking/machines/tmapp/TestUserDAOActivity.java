package com.thinking.machines.tmapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class TestUserDAOActivity extends Activity {

    private EditText idEditText;
    private EditText usernameEditText;
    private EditText mobileNumberEditText;
    private EditText userIdEditText;
    private EditText startupStatusEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_user_dao);
        idEditText=(EditText)this.findViewById(R.id.id);
        usernameEditText=(EditText)this.findViewById(R.id.username);
        mobileNumberEditText=(EditText)this.findViewById(R.id.mobileNumber);
        userIdEditText=(EditText)this.findViewById(R.id.userId);
        startupStatusEditText=(EditText)this.findViewById(R.id.starupStatus);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_user_dao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addButtonClickedHandler(View view) {
        String vUsername=usernameEditText.getText().toString();
        String vMobileNumber=mobileNumberEditText.getText().toString();
        String vUserId=userIdEditText.getText().toString();
        String vStartupStatus=startupStatusEditText.getText().toString();
        UserDAO userDAO=new UserDAO(this);
        userDAO.add(vUsername,vMobileNumber,vUserId,vStartupStatus);
    }
    public void getByIdButtonClickedHandler(View view) {
        UserDAO userDAO=new UserDAO(this);
        int vId=Integer.parseInt(idEditText.getText().toString());
        User user=userDAO.getById(vId);
        usernameEditText.setText(user.getUsername());
        mobileNumberEditText.setText(user.getMobileNumber());
        userIdEditText.setText(user.getUserId());
        startupStatusEditText.setText(user.getStartupStatus());
    }
    public void updateButtonClickedHandler(View view) {
        int vId=Integer.parseInt(idEditText.getText().toString());
        String vUsername=usernameEditText.getText().toString();
        String vMobileNumber=mobileNumberEditText.getText().toString();
        String vUserId=userIdEditText.getText().toString();
        String vStartupStatus=startupStatusEditText.getText().toString();
        UserDAO userDAO=new UserDAO(this);
        userDAO.update(vId, vUsername, vMobileNumber, vUserId, vStartupStatus);
    }
    public void deleteButtonClickedHandler(View view) {
        UserDAO userDAO=new UserDAO(this);
        int vId=Integer.parseInt(idEditText.getText().toString());
        userDAO.remove(vId);
    }
    public void getAllButtonClickedHandler(View view) {
        UserDAO userDAO=new UserDAO(this);
        ArrayList<User> users=userDAO.getAll();
        User user;
        int x=0;
        String g="";
        while(x<users.size())
        {
            user=users.get(x);
            g+=user.getId();
            g+=",";
            g+=user.getUsername();
            g+=",";
            g+=user.getMobileNumber();
            g+=",";
            g+=user.getUserId();
            g+=",";
            g+=user.getStartupStatus();
            x++;
            if(x<users.size())
            {
                g+=",";
            }
        }
        Toast.makeText(this,g,Toast.LENGTH_LONG).show();
    }

    public void getCountButtonClickedHandler(View view) {
        UserDAO userDAO=new UserDAO(this);
        Toast.makeText(this,"Count "+userDAO.getCount(),Toast.LENGTH_LONG).show();
    }
}
