package com.example.matsuhisahironobu.jsontestapp02;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyActivity extends Activity {

     public static final String URL = "http://agile-basin-1356.herokuapp.com/entries.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //
        Log.v("Start", "-------------");

        AsyncGet asyncGet = new AsyncGet(new AsyncCallback() {
            @Override
            public void onPreExecute() {
                Log.v("onPreExecute", "-> ？");
            }

            @Override
            public void onPostExecute(String result) {
                Log.v("onPostExecute", result);

                try{
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray  jsonArray  = jsonObject.getJSONArray("data");

                    for(int i=0; i < jsonArray.length(); i++)
                    {
                        JSONObject dataObject = jsonArray.getJSONObject(i);
                        Log.v("Data -> ", dataObject.getString("title"));
                    }
                }catch (JSONException e){
                    Log.v("JsonError", e.getMessage());
                }
            }

            @Override
            public void onProgressUpdate(int progress) {
                Log.v("onProgressUpdate", "-> update");
            }

            @Override
            public void onCancelled() {
                Log.v("onCancelled", "キャンセル");
            }
        });
        asyncGet.execute(URL);

        /*
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);
        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(request);
            Log.v("Response", URL);
        }catch (Exception e){
            Log.v("Error", e.getMessage());
        }
        */

        Log.v("End", "-------------");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
