package com.utc.applogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/*
@autores:Isamarqui
@creación/ 27/04/2021
@fModificación 02/05/2021
@descripción: Gestion cliente.
*/
public class ventaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);
    }

    public void volverVenta(View vista){ //metodo para volver al menu
        Intent ventaMenu= new Intent(getApplicationContext(),MenuActivity.class); // invocar a pantalla inical
        startActivity(ventaMenu); //iniciamos la pantalla de Menu
    }
}