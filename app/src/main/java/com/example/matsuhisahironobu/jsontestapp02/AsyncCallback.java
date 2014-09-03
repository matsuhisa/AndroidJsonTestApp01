package com.example.matsuhisahironobu.jsontestapp02;

/**
 * Created by matsuhisahironobu on 2014/09/02.
 * http://qiita.com/otoyo/items/70cfa89bfa93ffdf6c9a
 */

public interface AsyncCallback {
    void onPreExecute();
    void onPostExecute(String result);
    void onProgressUpdate(int progress);
    void onCancelled();
}
