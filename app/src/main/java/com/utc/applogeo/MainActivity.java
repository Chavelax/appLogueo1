package com.utc.applogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/*
@autores:Isamarqui
@creación/ 27/04/2021
@fModificación 02/05/2021
@descripción: inicio de sesion.
*/
public class MainActivity extends AppCompatActivity {
    //entrada

    EditText txtEmailLogin, txtPasswordLogin;     //creacion objetos
    BaseDatos bdd;//creo objetito tipo bdd

    CheckBox boxGuardarBtn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";

    //proceso1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mapero de elementos Xml a objetos JAVA
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtPasswordLogin = (EditText) findViewById(R.id.txtPasswordLogin);
        bdd=new BaseDatos(getApplicationContext());

        //iniciarElementos();
        //revisarSesion();
        //if (revisarSesion()){
        //    startActivity(new Intent(this,MenuActivity.class));
        //}
    }

    //proceso2
    public void abrirPantallaRegistro(View vista){ //metodo para abrir ventana de registro
        Intent pantallaRegistro= new Intent(getApplicationContext(),RegistroActivity.class); //creando in intnt para invocar a registro activicty
        startActivity(pantallaRegistro); //iniciamos la pantalla de Registro

    }

    //proceso 3 para validar usuario
    public void iniciarSesion(View vista){
        //logica de negocio para capturar los valores ingresados en el campo email y password y consultarlos en la BDD
        //capturar valores ingresados por el usuario
        String email=txtEmailLogin.getText().toString();
        String password = txtPasswordLogin.getText().toString();

        Cursor usuarioEncontrado = bdd.obtenerUsuarioPorEmailPassword(email,password);//consultar user en bdd/ uso "Cursor"
        if(usuarioEncontrado != null){

            String emailBdd = usuarioEncontrado.getString(3).toString();//Obtener valor del email de BDD
            String nombreBdd=usuarioEncontrado.getString(2).toString();//Obtener valor del email de BDD
            //guardarSesion(boxGuardarBtn.isChecked());//

            Toast.makeText(getApplicationContext(), "Bienvenido " + nombreBdd, Toast.LENGTH_LONG).show();
            finish();//cerrando el formulario de inicio de seseion

            Intent ventanaMenu = new Intent(getApplicationContext(),MenuActivity.class);//objeto para manejar activity menu
            startActivity(ventanaMenu);//abrir el activity del menu de opciones

        }else{
            //para el caso de que el usuarioEncontrado sea nulo se muestra un mensaje informativo al usuario
            Toast.makeText(getApplicationContext(), "Email o contraseña incorrectos", Toast.LENGTH_LONG).show();
            txtPasswordLogin.setText(""); //limpiamos el campo de la contraseña
        }
    }



    //proceso 4 guardar sesion sharedpreferences
    //public void guardarSesion(boolean checked){
        //editor.putBoolean(llave,checked);
        //editor.apply();
    //}

    //proceso 5 validacion sesion iniciada
    //public boolean revisarSesion(){
        //boolean sesion=this.preferences.getBoolean(llave,false);
        //return false;
    //}

    //public void iniciarElementos(){
        //preferences = this.getPreferences(Context.MODE_PRIVATE);
        //editor=preferences.edit();
        //boxGuardarBtn=findViewById(R.id.boxGuardar);
        //.
   // }
}