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
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void cerrarSesion(View vista){ //metodo para cerrar sesion
        Intent pantallaInicio= new Intent(getApplicationContext(), MainActivity.class); // invocar a pantalla inical
        startActivity(pantallaInicio); //iniciamos la pantalla de Registro
    }

    public void clientesv(View vista){ //metodo para cerrar sesion
        Intent pantallaClientes= new Intent(getApplicationContext(), clienteActivity.class); // invocar a pantalla inical
        startActivity(pantallaClientes); //iniciamos la pantalla de Registro
    }

    public void productosv(View vista){ //metodo para cerrar sesion
        Intent pantallaProductos= new Intent(getApplicationContext(), productoActivity.class); // invocar a pantalla inical
        startActivity(pantallaProductos); //iniciamos la pantalla de Registro
    }

    public void ventasv(View vista){ //metodo para cerrar sesion
        Intent pantallaVentas= new Intent(getApplicationContext(),ventaActivity.class); // invocar a pantalla inical
        startActivity(pantallaVentas); //iniciamos la pantalla de Registro
    }
}