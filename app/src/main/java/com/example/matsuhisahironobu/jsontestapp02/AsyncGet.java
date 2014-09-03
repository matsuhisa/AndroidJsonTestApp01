package com.example.matsuhisahironobu.jsontestapp02;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;


public class AsyncGet extends AsyncTask<String, Integer, String> {
    private AsyncCallback _asyncCallBack = null;

    public AsyncGet(AsyncCallback asyncCallback)
    {
        this._asyncCallBack = asyncCallback;
    }

    protected String doInBackground(String... urls)
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(urls[0]);

        Log.v("AsyncGet.java", urls[0]);

        try{
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(outputStream);
                return outputStream.toString();
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    protected void onPreExcute(){
        super.onPreExecute();
        this._asyncCallBack.onPreExecute();
    }

    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        this._asyncCallBack.onProgressUpdate(values[0]);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this._asyncCallBack.onPostExecute(result);
    }

    protected void onCancelled() {
        super.onCancelled();
        this._asyncCallBack.onCancelled();
    }
}
