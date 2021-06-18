package com.utc.applogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

/*
@autores:Isamarqui
@creación/ 27/04/2021
@fModificación 02/05/2021
@descripción: Gestion cliente.
*/
public class productoActivity extends AppCompatActivity {
    //Entrada de datos
    EditText txtNombreProducto,txtPrecioProducto, txtStockProducto,txtCaducaProducto;
    RadioButton btnIvaProducto;
    BaseDatos miBdd;

    //salida
    ListView lstProductos;
    ArrayList<String> listaProductos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        //mapeo elementes
        txtNombreProducto=(EditText) findViewById(R.id.txtNombreProducto);
        txtPrecioProducto=(EditText) findViewById(R.id.txtPrecioProducto);
        btnIvaProducto=(RadioButton) findViewById(R.id.btnIvaProducto);
        txtStockProducto=(EditText) findViewById(R.id.txtStockProducto);
        txtCaducaProducto=(EditText) findViewById(R.id.txtCaducaproducto);
        lstProductos=(ListView) findViewById(R.id.lstProductos);
        miBdd=new BaseDatos(getApplicationContext());
        consultarDatoss();//llamando metodo para cargar datos en listview
    }

    public void limpiarCamposs(View vista){
        txtNombreProducto.setText("");//trl+alt+l formato codigo
        txtPrecioProducto.setText("");
        txtStockProducto.setText("");
        txtCaducaProducto.setText("");
        txtNombreProducto.requestFocus();
    }

    public void guardarProducto(View vista){
        String nombrep = txtNombreProducto.getText().toString();
        String preciop = txtPrecioProducto.getText().toString();
        String ivap = btnIvaProducto.getText().toString();
        String stockp = txtStockProducto.getText().toString();
        String caducap = txtCaducaProducto.getText().toString();
//----------------------------------------------------------------------
        int error=0;
        if (Double.parseDouble(preciop) < 1) {
            error++;
            txtPrecioProducto.setError("precio Debe ser mayor a 0");
            txtNombreProducto.requestFocus();
        }
        if (Double.parseDouble(stockp) < 1) {
            error++;
            txtStockProducto.setError("Stock Debe ser mayor a 0");
            txtNombreProducto.requestFocus();
        }
//-------------------------------------------------------------------------
        if(!nombrep.equals("")&& !preciop.equals("")&& !stockp.equals("")&& !caducap.equals("")){
            miBdd.agregarProductos(nombrep, preciop, ivap, stockp, caducap);
            limpiarCamposs(null);
            Toast.makeText(getApplicationContext(),"Producto registrado", Toast.LENGTH_LONG).show();
            consultarDatoss();
        }else{
            Toast.makeText(getApplicationContext(),"Complete todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void consultarDatoss(){//metodo pa´consultar clientes guardados
        listaProductos.clear();
        Cursor productosObtenidos=miBdd.obtenerProductos();//consultando prodtc y guardar en cursor
        if(productosObtenidos!= null){
            //proceso cuando si se encuentra
            do{
                String id=productosObtenidos.getString(0).toString();//captura indice del id client
                String nombrep =productosObtenidos.getString(1).toString();//captura indice apellido
                String preciop =productosObtenidos.getString(2).toString();//captura indice nombre
                String stockp = productosObtenidos.getString(4).toString();//captura stock
                listaProductos.add(id+":        " +nombrep+"        " +preciop+ "        " +stockp);
                ArrayAdapter<String> adaptadorProductos=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaProductos);
                lstProductos.setAdapter(adaptadorProductos);
            }while (productosObtenidos.moveToNext());//valida si aun existen clientes en el cursor

        }else{
            Toast.makeText(getApplicationContext(), "No se encontró Productos", Toast.LENGTH_LONG).show();
        }
    }

    public void volverProducto(View vista){ //metodo para volver al menu
        Intent productoMenu= new Intent(getApplicationContext(),MenuActivity.class); // invocar a pantalla inical
        startActivity(productoMenu); //iniciamos la pantalla de Menu
    }
}