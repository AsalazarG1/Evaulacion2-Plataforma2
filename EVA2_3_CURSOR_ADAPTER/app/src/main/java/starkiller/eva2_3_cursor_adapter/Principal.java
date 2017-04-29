package starkiller.eva2_3_cursor_adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    SQLiteDatabase sqlMIDB;
    ListView listDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        listDatos = (ListView)findViewById(R.id.listDatos);
        sqlMIDB = openOrCreateDatabase("misdatos",MODE_PRIVATE,null);
        try{
            sqlMIDB.execSQL("create table tblDatos(miID integer PRIMARY KEY autoincrement, " +
                    "nombre text, "+
                    "apellido text, "+
                    "edad integer);");
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        Cursor cDatos = sqlMIDB.rawQuery("select * from tblDatos",null);
        listDatos.setAdapter(new CursorAdaper(this,cDatos,0));
        ContentValues cvDatos = new ContentValues();
        cvDatos.put("nombre", "Alejandro");
        cvDatos.put("apellido", "Salazar");
        cvDatos.put("edad", "22");
        sqlMIDB.insert("tblDatos", null, cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Jose");
        cvDatos.put("apellido", "Castro");
        cvDatos.put("edad", "25");
        sqlMIDB.insert("tblDatos", null, cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Fernando");
        cvDatos.put("apellido", "Venzor");
        cvDatos.put("edad", "22");
        sqlMIDB.insert("tblDatos", null, cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Eduardo");
        cvDatos.put("apellido", "Saenz");
        cvDatos.put("edad", "22");
        sqlMIDB.insert("tblDatos", null, cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Angelica");
        cvDatos.put("apellido", "Marquez");
        cvDatos.put("edad", "22");
        sqlMIDB.insert("tblDatos", null, cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Lolito");
        cvDatos.put("apellido", "Ayala");
        cvDatos.put("edad", "22");
        sqlMIDB.insert("tblDatos", null, cvDatos);
        cvDatos.clear();
      //  long Lreg =  sqlMIDB.insert("tblDatos",null,cvDatos);
       // Toast.makeText(this, "Registro: " + Lreg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
