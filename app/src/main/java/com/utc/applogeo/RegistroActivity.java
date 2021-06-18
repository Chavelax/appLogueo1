package com.utc.applogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
@autores:Isamarqui
@creación/ 12/05/2021
@fModificación 02/05/2021
@descripción: registro de usuario.
*/
public class RegistroActivity extends AppCompatActivity {
    //entrada
    EditText txtApellidoRegistro, txtNombreRegistro, txtEmailRegistro,txtPasswordRegistro, txtPasswordConfirmada; // definiendo objetos para capturar datos de la lista
    BaseDatos miBdd;// creando un objeto para acceder a los procesos de la BDD SQlite

    //proceso1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //mapeo de elementos
        txtApellidoRegistro=(EditText)findViewById(R.id.txtApellidoRegistro);
        txtNombreRegistro= (EditText)findViewById(R.id.txtNombreRegistro);
        txtEmailRegistro= (EditText)findViewById(R.id.txtEmailRegistro);
        txtPasswordRegistro=(EditText)findViewById(R.id.txtPasswordRegistro);
        txtPasswordConfirmada= (EditText)findViewById(R.id.txtPasswordConfirmada);

        miBdd= new BaseDatos(getApplicationContext()); //instanciar /construir la base de datos en el objeto mi bdd

    }
    //proceso2
    public void cerrarPantallaRegistro(View vista){
        finish();//cerrando la pantalla de registro
    }

    //proceso 3
    public void registrarUsuario(View vista){
        //capturando los valores ingresados por el usuario en variables JAva de tipo String
        String apellido=txtApellidoRegistro.getText().toString();
        String nombre=txtNombreRegistro.getText().toString();
        String email=txtEmailRegistro.getText().toString();
        String password=txtPasswordRegistro.getText().toString();
        String passwordConfirmada=txtPasswordConfirmada.getText().toString();
        //validacion que las contraseñas insertadas sean iguales
        if(password.equals(passwordConfirmada)){  // si passsword es igual a password confirmada
            // si se cumple -> proceso de insercion
            miBdd.agregarUsuarios(apellido,nombre,email,password); //Invocando al metodo agregar usuaro del objeto miBdd para insertar datos en SQLite
            Toast.makeText(getApplicationContext(),"Usuario almacenado Exitosamente",Toast.LENGTH_LONG).show(); // mostrando un mensade de confirmacion
            finish();

        }else{
            // si no se cumple -> mensaje de error
            Toast.makeText(getApplicationContext(),"la contraseña no coincide",Toast.LENGTH_LONG).show(); // mostrando un mensade de error/alerta
        }
    }
}