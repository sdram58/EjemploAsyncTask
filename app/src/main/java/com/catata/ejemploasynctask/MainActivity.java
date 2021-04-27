package com.catata.ejemploasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvContador;
    Button btnIniciar;
    EditText etInicio;
    ProgressBar progressBar;

    MiTareaAsincrona tareaAsincrona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvContador = (TextView)findViewById(R.id.textView);
        btnIniciar = (Button)findViewById(R.id.button);
        etInicio = (EditText) findViewById(R.id.etInicio);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);



        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                tareaAsincrona = new MiTareaAsincrona(new MiTareaAsincrona.ContadorInterface() {
                    @Override
                    public void OnUpdateCounter(String valor) {
                        tvContador.setText(valor);
                    }

                    @Override
                    public void OnEndCounter(String valor) {
                        tvContador.setText(valor + " FIN! ");
                        progressBar.setVisibility(View.GONE);
                    }
                });
                tareaAsincrona.execute(Integer.parseInt(etInicio.getText().toString()));
            }
        });

    }

    
}