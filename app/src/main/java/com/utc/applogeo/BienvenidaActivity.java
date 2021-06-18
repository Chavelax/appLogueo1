package com.utc.applogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/*
@autores:Isamarqui
@creación/ 19/05/2021
@fModificación 19/05/2021
@descripción: ventana de bienvenida que es visible por 4 seg.
*/

public class BienvenidaActivity extends AppCompatActivity {
    //proceso1 ->metodo que se ejecuta de manera automatica cuando inicia la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        //definir un hilo -> simula un crnometro que ejecuta una accion con base a un tiempo determinado
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //acciones que necesite agregar despues del timpo
                Intent ventanaLogin=new Intent(getApplicationContext(),MainActivity.class); //construyendo un objeto de tipo ventana para poder abrir la ventana de login
                startActivity(ventanaLogin); //solicitamos que habra el formulario de login
                finish(); //cerrando la activity de bienvenida
            }
        }, 4000);
    }
}