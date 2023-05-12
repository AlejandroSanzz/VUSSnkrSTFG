package com.example.vussnkrs;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos extends SQLiteOpenHelper {

        // Nombre de la base de datos
        public static final String NOMBRE_BASE_DE_DATOS = "SNKRSDB";

        // Versión de la base de datos
        public static final int VERSION_BASE_DE_DATOS = 1;

        // Nombre de la tabla "productos"
        public static final String TABLA_PRODUCTOS = "productos";

        // Nombre de las columnas de la tabla "productos"
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_SKU = "sku";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_CATEGORIA = "categoria";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
        public static final String COLUMNA_TALLA = "talla";
        public static final String COLUMNA_PRECIO = "precio";
        public static final String COLUMNA_IMAGEN = "imagen";
        public static final String COLUMNA_STOCK = "stock";
        public static final String COLUMNA_PROVEEDOR = "proveedor";

        // Sentencia SQL para crear la tabla "productos"
        public static final String CREAR_TABLA_PRODUCTOS = "CREATE TABLE " + TABLA_PRODUCTOS + "("
                + COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMNA_SKU + " INTEGER,"
                + COLUMNA_NOMBRE + " TEXT,"
                + COLUMNA_CATEGORIA + " TEXT,"
                + COLUMNA_DESCRIPCION + " TEXT,"
                + COLUMNA_TALLA + " INTEGER,"
                + COLUMNA_PRECIO + " REAL,"
                + COLUMNA_IMAGEN + " TEXT,"
                + COLUMNA_STOCK + " INTEGER,"
                + COLUMNA_PROVEEDOR + " TEXT"
                + ")";

        // Constructor de la clase
        public BaseDeDatos(Context context) {
            super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
        }

        //public BaseDeDatos(String sku, String nombre, String categoria, String descripcion, String talla, String precio, String imagen, String stock, String proveedor) {

        //}


        // Método para crear la tabla "productos"
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREAR_TABLA_PRODUCTOS);
        }

        // Método para actualizar la base de datos
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Aquí puedes agregar código para actualizar la base de datos si es necesario
        }
        // Método para abrir la base de datos con permisos de lectura
        public SQLiteDatabase getReadableDatabase() {
                return super.getReadableDatabase();
        }

        // Método para abrir la base de datos con permisos de escritura
        public SQLiteDatabase openWritable() {
            return getWritableDatabase();
        }

        // Método para cerrar la base de datos
        public void close(SQLiteDatabase db) {
            db.close();
        }}
        /*
        public void mostrarProductos(ListView listView) {
                // Obtener una referencia a la base de datos
                SQLiteDatabase db = getReadableDatabase();

                // Definir una lista para almacenar los datos
                List<String> productos = new ArrayList<>();

                // Definir la consulta SQL
                String consulta = "SELECT * FROM " + TABLA_PRODUCTOS;

                // Ejecutar la consulta y obtener un cursor con los resultados
                Cursor cursor = db.rawQuery(consulta, null);

                // Recorrer el cursor y agregar los datos a la lista
                if (cursor.moveToFirst()) {
                        do {
                                String producto = "ID: " + cursor.getInt(0) + ", SKU: " + cursor.getInt(1) + ", Nombre: " + cursor.getString(2)
                                        + ", Categoría: " + cursor.getString(3) + ", Descripción: " + cursor.getString(4) + ", Talla: " + cursor.getInt(5)
                                        + ", Precio: " + cursor.getFloat(6) + ", Imagen: " + cursor.getString(7) + ", Stock: " + cursor.getInt(8)
                                        + ", Proveedor: " + cursor.getString(9);
                                productos.add(producto);
                        } while (cursor.moveToNext());
                }

                // Cerrar el cursor y la base de datos
                cursor.close();
                db.close();

                // Mostrar los datos en la aplicación (por ejemplo, en un ListView)
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productos);
                listView.setAdapter(adapter);
        }

}
/*
        public List<BaseDeDatos> buscarProductos(String textoBusqueda) {
                SQLiteDatabase db = this.getReadableDatabase();
                List<BaseDeDatos> listaProductos = new ArrayList<>();

                String[] columnas = {
                        COLUMNA_SKU,
                        COLUMNA_NOMBRE,
                        COLUMNA_CATEGORIA,
                        COLUMNA_DESCRIPCION,
                        COLUMNA_TALLA,
                        COLUMNA_PRECIO,
                        COLUMNA_IMAGEN,
                        COLUMNA_STOCK,
                        COLUMNA_PROVEEDOR
                };

                String selection = COLUMNA_SKU + " LIKE ? OR " +
                        COLUMNA_NOMBRE + " LIKE ? OR " +
                        COLUMNA_CATEGORIA + " LIKE ? ";
                String[] selectionArgs = { "%" + textoBusqueda + "%",
                        "%" + textoBusqueda + "%",
                        "%" + textoBusqueda + "%",
                        "%" + textoBusqueda + "%" };

                Cursor cursor = db.query(TABLA_PRODUCTOS,
                        columnas,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);

                while (cursor.moveToNext()) {
                        String sku = cursor.getString(cursor.getColumnIndex(COLUMNA_SKU));
                        @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRE));
                        String categoria = cursor.getString(cursor.getColumnIndex(COLUMNA_CATEGORIA));
                        String descripcion = cursor.getString(cursor.getColumnIndex(COLUMNA_DESCRIPCION));
                        String talla = cursor.getString(cursor.getColumnIndex(COLUMNA_TALLA));
                        String precio = cursor.getString(cursor.getColumnIndex(COLUMNA_PRECIO));
                        String imagen = cursor.getString(cursor.getColumnIndex(COLUMNA_IMAGEN));
                        String stock = cursor.getString(cursor.getColumnIndex(COLUMNA_STOCK));
                        String nombreProveedor = cursor.getString(cursor.getColumnIndex(COLUMNA_PROVEEDOR));
                        BaseDeDatos producto = new BaseDeDatos(sku, nombre, categoria, descripcion, talla, precio, imagen, stock, nombreProveedor);
                        listaProductos.add(producto);
                }

                cursor.close();
                db.close();

                return listaProductos;
        }
    }
/*
    private static final String NOMBRE_BASE_DE_DATOS = "SNKRSDB";
    private static final int VERSION_BASE_DE_DATOS = 1;
    private static final String CREAR_TABLA_PRODUCTOS =
            "CREATE TABLE productos (" +
                    "SKU INTEGER PRIMARY KEY," +
                    "Nombre TEXT," +
                    "Categoria TEXT," +
                    "Descripcion TEXT," +
                    "Talla INTEGER," +
                    "Precio DOUBLE," +
                    "Imagen BLOB," +
                    "Stock INTEGER," +
                    "NombreProveedor TEXT" +
                    ")";

    private int sku;
    private String nombre;
    private String categoria;
    private String descripcion;
    private int talla;
    private double precio;
    private byte[] imagen;
    private int stock;
    private String nombreProveedor;

    public int getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getTalla() {
        return talla;
    }

    public double getPrecio() {
        return precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public int getStock() {
        return stock;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public BaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_PRODUCTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS productos");
        onCreate(db);
    }

    public long insertarProducto(BaseDeDatos producto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("SKU", producto.getSku());
        valores.put("Nombre", producto.getNombre());
        valores.put("Categoria", producto.getCategoria());
        valores.put("Descripcion", producto.getDescripcion());
        valores.put("Talla", producto.getTalla());
        valores.put("Precio", producto.getPrecio());
        valores.put("Imagen", producto.getImagen());
        valores.put("Stock", producto.getStock());
        valores.put("NombreProveedor", producto.getNombreProveedor());

        long id = db.insert("productos", null, valores);

        db.close();

        return id;
    }

    public int actualizarProducto(BaseDeDatos producto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("SKU", producto.getSku());
        valores.put("Nombre", producto.getNombre());
        valores.put("Categoria", producto.getCategoria());
        valores.put("Descripcion", producto.getDescripcion());
        valores.put("Talla", producto.getTalla());
        valores.put("Precio", producto.getPrecio());
        valores.put("Imagen", producto.getImagen());
        valores.put("Stock", producto.getStock());
        valores.put("NombreProveedor", producto.getNombreProveedor());

        int filasActualizadas = db.update("productos", valores, "SKU = ?", new String[]{String.valueOf(producto.getSku())});

        db.close();

        return filasActualizadas;
    }

    public void eliminarProducto(int sku) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "SKU = ?";
        String[] whereArgs = { String.valueOf(sku) };
        db.delete("productos", whereClause, whereArgs);
        db.close();
    }

}*/