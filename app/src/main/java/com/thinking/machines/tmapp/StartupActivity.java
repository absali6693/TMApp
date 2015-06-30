package com.thinking.machines.tmapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class StartupActivity extends Activity {
    private static StartupActivity startupActivity;
    @Override
    protected void onStart() {
        startupActivity=this;
        super.onStart();
    }

    public static StartupActivity getStartupActivity()
    {
        return startupActivity;
    }
    public void setSMSData(String smsData)
    {
        Toast.makeText(this,smsData,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void signUpButtonClickHandler(View view) {
        Intent intent;
        intent=new Intent(this,SignupActivity.class);
        this.startActivity(intent);
       /* MyTask myTask=new MyTask();
        myTask.execute("Hello");*/


    }

    class MyTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            String url="http://10.0.2.2:8080/one.com/servlet/Whatever";

            DefaultHttpClient defaultHttpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(url);
            try {
                HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                InputStream inputStream=httpEntity.getContent();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"iso-8859-1");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader,1);
                StringBuilder stringBuilder=new StringBuilder();
                String g;
                while(true)
                {
                    g=bufferedReader.readLine();
                    if(g==null) break;
                    stringBuilder.append(g+"\n");
                }
                inputStream.close();
                return stringBuilder.toString();
            }catch(Exception e)
            {
                return e.getMessage();
            }
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String s) {
            try {
            JSONObject jsonObject = new JSONObject(s);
            String name=jsonObject.getString("name");
            boolean indian=jsonObject.getBoolean("indian");
            Toast.makeText(StartupActivity.this, name + "," + indian, Toast.LENGTH_LONG).show();
        }catch(Exception e)
        {
            Toast.makeText(StartupActivity.this,"Problem"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
            super.onPostExecute(s);
        }
    }

}


