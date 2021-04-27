package com.catata.ejemploasynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MiTareaAsincrona extends AsyncTask<Integer,String, Integer> {

    ContadorInterface listener;
    public MiTareaAsincrona(ContadorInterface listener){
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("onPreExecute", Thread.currentThread().getName());
    }



    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Log.i("ASINCRONO", integer.toString());
        Log.i("onPostExecute", Thread.currentThread().getName());

        listener.OnEndCounter(integer.toString());
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        Log.i("onProgressUpdate", Thread.currentThread().getName());
        Log.i("onProgressUpdate", values[0]);

        listener.OnUpdateCounter(values[0]);


    }

    @Override
    protected Integer doInBackground(Integer... numeros) {
        int inicio = numeros[0];

        for(int i=0;i<10;i++){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(Integer.toString(++inicio));
        }


        Log.i("doInBackground", Thread.currentThread().getName());
        return inicio;
    }

    public interface ContadorInterface{
        void OnUpdateCounter(String valor);
        default void OnEndCounter(String valor){};
    }
}
