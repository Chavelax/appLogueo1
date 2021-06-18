package com.utc.applogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
@autores:Isamarqui
@creación/ 16/06/2021
@fModificación 16/06/2021
@descripción: Gestion cliente.
*/
//Actividad para editar eliminar datos registrados en gestion dee clientes
public class EditarClienteActivity extends AppCompatActivity {
    String id, cedula, apellido, nombre, telefono,direccion;//capturando variables
    TextView texIdClienteEditar;
    EditText txtCedulaClienteEditar, txtApellidoClienteEditar, txtNombreClienteEditar, txtTelefonoClienteEditar,
            txtDireccionClienteEditar;
    BaseDatos miBdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
        //mapeo elementos
        texIdClienteEditar=(TextView)findViewById(R.id.texIdClienteEditar);
        txtCedulaClienteEditar=(EditText)findViewById(R.id.txtCedulaClienteEditar);
        txtApellidoClienteEditar=(EditText) findViewById(R.id.txtApellidoClienteEditar);
        txtNombreClienteEditar=(EditText) findViewById(R.id.txtNombreClienteEditar);
        txtTelefonoClienteEditar=(EditText) findViewById(R.id.txtTelefonoClienteEditar);
        txtDireccionClienteEditar=(EditText) findViewById(R.id.txtDireccionClienteEditar);
        Bundle parametrosExtra=getIntent().getExtras();//capturar parametros pasados a la actividad

        if(parametrosExtra!=null){
            try {
                id=parametrosExtra.getString("id");
                cedula=parametrosExtra.getString("cedula");
                apellido= parametrosExtra.getString("apellido");
                nombre=parametrosExtra.getString("nombre");
                telefono=parametrosExtra.getString(telefono);
                direccion=parametrosExtra.getString("direccion");

            }catch (Exception ex){
                Toast.makeText(getApplicationContext(), "Error en proceso de solicitud "+ex.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        texIdClienteEditar.setText(id);
        txtCedulaClienteEditar.setText(cedula);
        txtApellidoClienteEditar.setText(apellido);
        txtNombreClienteEditar.setText(nombre);
        txtTelefonoClienteEditar.setText(telefono);
        txtDireccionClienteEditar.setText(direccion);
        miBdd = new BaseDatos(getApplicationContext());

        //-----------//---------------------
    }
    //metodo volver de ventana emergente(editar/eliminar)
    public void volver(View vista){
        finish();
        Intent ventanaGestionClientes=new Intent(getApplicationContext(),clienteActivity.class);
        startActivity(ventanaGestionClientes);
    }
    //metodo eliminar cliente
    public void eliminarCliente(View vista){
        AlertDialog.Builder estructuraConfirmacion=new AlertDialog.Builder(this).setTitle("CONFIRMACIóN")
                .setMessage("Esta seguro de Eliminar el cliente seleccionado?")
                .setPositiveButton("Si, Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        miBdd.eliminarCliente(id);
                        Toast.makeText(getApplicationContext(),"Eliminacion Exitosa", Toast.LENGTH_SHORT).show();
                        volver(null);
                    }
                })
                .setNegativeButton("No, Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Eliminacion Cancelada", Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(true);
        AlertDialog cuadroDialogo=estructuraConfirmacion.create();
        cuadroDialogo.show();
    }

    //metodo actualizar el registro del cliente
    public void actualizarClient(View vista){
        String cedula=txtCedulaClienteEditar.getText().toString();
        String apellido=txtApellidoClienteEditar.getText().toString();
        String nombre=txtNombreClienteEditar.getText().toString();
        String telefono=txtTelefonoClienteEditar.getText().toString();
        String direccion=txtDireccionClienteEditar.getText().toString();

        if(!cedula.equals("")&& !apellido.equals("")&& !nombre.equals("")&& !telefono.equals("")&& !direccion.equals("")){
            miBdd.actualizarClientes(cedula, apellido, nombre, telefono, direccion, id);
            Toast.makeText(getApplicationContext(), "actualizacion Exitosa", Toast.LENGTH_LONG).show();
            volver(null);
        }else{
            Toast.makeText(getApplicationContext(),"Complete todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}