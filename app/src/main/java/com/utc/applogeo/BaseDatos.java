
package com.utc.applogeo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
@autores:Isamarqui
@creación/ 27/04/2021
@fModificación 02/05/2021
@descripción: Gestion cliente.
*/
public class BaseDatos extends SQLiteOpenHelper {
    private  static  final String nombreBdd="bdd_usuarios_noveno_a"; //definiendo el nombre dela Bdd
    private  static  final int versionBdd=3; //definiendo la version de la BDD
    private  static  final String tablaUsuario ="create table usuario(id_usu integer primary key autoincrement," +
            " apellido_usu text, nombre_usu text, email_usu text, password_usu text)"; // definiendo estructura de la tabla usuarios
    private static final String tablaCliente="create table cliente(id_cli integer primary key autoincrement," +
            "cedula_cli text, apellido_cli text, nombre_cli text, telefono_cli text, direccion_cli text)";//estruct table clientes
    private static final String tablaProducto="create table producto(id_pro integer primary key autoincrement," +
            "nombrep_pro text, preciop_pro DECIMAL(2,0), ivap_pro INTEGER, stockp_pro INTEGER, caducap_pro DATETIME)";//estruct table produts

    //Constructor
    public BaseDatos(Context contexto){
        super(contexto,nombreBdd,null,versionBdd);
    }

    //proceso 1 crear la BDD
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaUsuario); // ejecutando el query DDl(sentencia de definicion de datos) para crear la tabla usuarios con sus atributos
        db.execSQL(tablaCliente);//same
        db.execSQL(tablaProducto);
    }
    //proceso 2: metodo que se ejecuta automaticamente cuando se detectan cambios en la version de nuestra bdd
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");//elimincacion de la version anterior de la tabla usuarios se puee usar otro comando Dll como alter table
        db.execSQL(tablaUsuario); //Ejecucion del codigo para crear la tabla usuaios con su nueva estructura

        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL(tablaCliente);

        db.execSQL("DROP TABLE IF EXISTS producto");
        db.execSQL(tablaProducto);

    }

    //proceso 3: metodo para insertar datos de la tabla usuarios , retorno true ciando inserto y false cuando hay algun error
    public boolean agregarUsuarios(String apellido, String nombre, String email, String password){
        SQLiteDatabase miBdd=getWritableDatabase(); //llamando a la base de datos en el objeto miBdd
        if(miBdd != null){ //validando que la base de datos exista(q no sea nula)
            miBdd.execSQL("insert into usuario(apellido_usu, nombre_usu, email_usu, password_usu) " +
                    "values('"+apellido+"','"+nombre+"','"+email+"','"+password+"')");    //ejecutando la sentencia de insercion de SQL
            miBdd.close(); //cerrando la conexion a la base de datos.
            return true; // valor de retorno si se inserto exitosamente.
        }
        return false; //retorno cuando no existe la BDD
    }

        /*crud
        c -> create
        r -> leer
        U -> ACTUALIZAR
        D -> eliminar*/

    //Proceso 4 para consultar usuarios por email y password.
    public Cursor obtenerUsuarioPorEmailPassword(String email, String password){
        SQLiteDatabase miBdd=getWritableDatabase(); // llamado a la base de datos
        //crear un cursor donde inserto la consulta sql y almaceno los resultados en el objeto usuario
        Cursor usuario = miBdd.rawQuery("select * from usuario where " +
                "email_usu='"+email+"' and password_usu = '"+password+"';", null);

        //validar si existe o no la consulta
        if(usuario.moveToFirst()){ //metodo movetofirst nueve al primer elemento encontrado si hay el usuario
            return usuario; //retornamos los datos encontrados
        }else{
            //no se encuentra informacion de usuaio -> no existe o porque el email y password son incorrectos
            return null; //devuelvo null si no hay
        }
    }

    //metood agregar clientes
    public boolean agregarClientes(String cedula,String apellido,String nombre,String telefono,String direccion){
        SQLiteDatabase miBdd=getWritableDatabase();
        if(miBdd != null){
            miBdd.execSQL("insert into cliente(cedula_cli, apellido_cli, nombre_cli,telefono_cli, direccion_cli) " +
                    "values  ('"+cedula+"','"+apellido+"','"+nombre+"','"+telefono+"','"+direccion+"')");
            return true;
        }
        return false;
    }
    //metodo consultar clientes
    public Cursor obtenerClientes(){
        SQLiteDatabase miBdd=getWritableDatabase();
        Cursor clientes = miBdd.rawQuery("select * from cliente;",null);
        if(clientes.moveToFirst()){
            miBdd.close();
            return clientes;
        }else {
            return null;
        }
    }

    //metodo actualizar cliente
    public boolean actualizarClientes(String cedula, String apellido, String nombre, String telefono, String direccion, String id){
        SQLiteDatabase miBdd=getWritableDatabase();
        if (miBdd!=null){//validando que la base de datos en realidad existe
            //Proceso de actualizacion
            miBdd.execSQL("update cliente set cedula_cli='"+cedula+"', " +
                    "apellido_cli='"+apellido+"', nombre_cli='"+nombre+"', " +
                    "telefono_cli='"+telefono+"',direccion_cli='"+direccion+"' where id_cli="+id);
            miBdd.close();
            return true;
        }
        return false;
    }

    //metodo eliminar cliente
    public boolean eliminarCliente(String id){
        SQLiteDatabase miBdd=getWritableDatabase();
        if (miBdd!=null){
            miBdd.execSQL("delete from cliente where id_cli="+id);
            miBdd.close();
            return true;
        }
        return false;
    }


    //metood agregar productos
    public boolean agregarProductos(String nombrep, String preciop, String ivap, String stockp, String caducap){
        SQLiteDatabase miBdd=getWritableDatabase();
        if(miBdd != null){
            miBdd.execSQL("insert into producto(nombrep_pro, preciop_pro, ivap_pro, stockp_pro,caducap_pro) " +
                    "values  ('"+nombrep+"','"+preciop+"', '"+ivap+"', '"+stockp+"','"+caducap+"')");
            return true;
        }
        return false;
    }
    //metodo obtener productos
    public Cursor obtenerProductos(){
        SQLiteDatabase miBdd=getWritableDatabase();
        Cursor producto = miBdd.rawQuery("select * from producto;",null);
        if(producto.moveToFirst()){
            miBdd.close();
            return producto;
        }else {
            return null;
        }
    }
}
