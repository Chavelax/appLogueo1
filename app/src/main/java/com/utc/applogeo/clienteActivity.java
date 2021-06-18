package com.utc.applogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
/*
@autores:Isamarqui
@creación/ 27/04/2021
@fModificación 02/05/2021
@descripción: Gestion cliente.
*/
public class clienteActivity extends AppCompatActivity {
    //Entrada de datos
    EditText txtCedulaCliente,txtApellidoCliente,txtNombreCliente,txtTelefonoCliente,txtDireccionCliente;
    BaseDatos miBdd;

    //salida
    ListView lstClientes;
    ArrayList<String> listaClientes=new ArrayList<>();
    Cursor clientesObtenidos;// declaracion variable global

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        //mapeo elementes
        txtCedulaCliente=(EditText) findViewById(R.id.txtCedulaCliente);
        txtApellidoCliente=(EditText) findViewById(R.id.txtApellidoCliente);
        txtNombreCliente=(EditText) findViewById(R.id.txtNombreCliente);
        txtTelefonoCliente=(EditText) findViewById(R.id.txtTelefonoCliente);
        txtDireccionCliente=(EditText) findViewById(R.id.txtDireccionCliente);
        lstClientes=(ListView) findViewById(R.id.lstClientes);
        miBdd=new BaseDatos(getApplicationContext());
        consultarDatos();//llamando metodo para cargar datos en lisview

        //Programacion de acciones cuando se da click en un elemento

        lstClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Seleccionaste la posicion: "+position,Toast.LENGTH_LONG).show();
                clientesObtenidos.moveToPosition(position);//ubicar la posicion seleccionada en la table
                String idCliente=clientesObtenidos.getString(0);
                String cedulaCliente=clientesObtenidos.getString(1);
                String apellidoCliente=clientesObtenidos.getString(2);
                String nombreCliente=clientesObtenidos.getString(3);
                String telefonoCliente=clientesObtenidos.getString(4);
                String direccionCliente=clientesObtenidos.getString(5);
                /*Toast.makeText(getApplicationContext(), idCliente+"-"+cedulaCliente+"-"+apellidoCliente+"-"+
                        nombreCliente+"-"+telefonoCliente+"-"+direccionCliente,Toast.LENGTH_LONG).show();*/
               //abriendo ventana para editar y eliminar la lista de clientes
                Intent ventanaEditarCliente=new Intent(getApplicationContext(),EditarClienteActivity.class);
                ventanaEditarCliente.putExtra("id: ",idCliente);//pasando id cliente como parametro
                ventanaEditarCliente.putExtra("cedulaCliente: ",cedulaCliente);
                ventanaEditarCliente.putExtra("apellidoCliente: ",apellidoCliente);
                ventanaEditarCliente.putExtra("nombreCliente: ",nombreCliente);
                ventanaEditarCliente.putExtra("telefonoCliente: ",telefonoCliente);
                ventanaEditarCliente.putExtra("direccionCliente: ",direccionCliente);
                startActivity(ventanaEditarCliente);
                finish();

            }
        });
    }
    public void limpiarCampos(View vista){
        txtCedulaCliente.setText("");//trl+alt+l formato codigo
        txtApellidoCliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDireccionCliente.setText("");
        txtCedulaCliente.requestFocus();
    }
    public void guardarCliente(View vista){
        String cedula = txtCedulaCliente.getText().toString();
        String apellido = txtApellidoCliente.getText().toString();
        String nombre = txtNombreCliente.getText().toString();
        String telefono = txtTelefonoCliente.getText().toString();
        String direccion = txtDireccionCliente.getText().toString();

        if(!cedula.equals("")&& !apellido.equals("")&& !nombre.equals("")&& !telefono.equals("")&& !direccion.equals("")){
            miBdd.agregarClientes(cedula, apellido, nombre, telefono, direccion);
            limpiarCampos(null);
            Toast.makeText(getApplicationContext(),"cliente registrado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"complete todos los campos", Toast.LENGTH_LONG).show();
        }
        consultarDatos();//recargar datos en la lista de clientes
    }
    public void consultarDatos(){//metodo pa´consultar clientes guardados
        listaClientes.clear();
         clientesObtenidos=miBdd.obtenerClientes();//consultandoclientes y guardar en cursor
        if(clientesObtenidos!= null){
            //proceso cuando si se encuentre
            do{
                String id=clientesObtenidos.getString(0).toString();//captura indice del id client
                String apellido =clientesObtenidos.getString(2).toString();//captura indice apellido
                String nombre =clientesObtenidos.getString(3).toString();//captura indice nombre
                listaClientes.add(id+": " +apellido+" " +nombre);
                ArrayAdapter<String> adaptadorClientes=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaClientes);
                lstClientes.setAdapter(adaptadorClientes);
            }while (clientesObtenidos.moveToNext());//valida si aun existen clientes en el cursor

        }else{
            Toast.makeText(getApplicationContext(), "No se encontró clientes", Toast.LENGTH_LONG).show();
        }
    }
    
    public void volverCliente(View vista){ //metodo para volver al menu
        Intent clienteMenu= new Intent(getApplicationContext(),MenuActivity.class); // invocar a pantalla inical
        startActivity(clienteMenu); //iniciamos la pantalla de Menu
    }
}