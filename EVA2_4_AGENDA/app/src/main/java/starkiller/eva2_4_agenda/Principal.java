package starkiller.eva2_4_agenda;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    ListView listCon;
    //Crear base de datos
    SQLiteDatabase sqldbConnect;
    Cursor c1;
    Cursoradapter adap;
    static final int RESULT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        sqldbConnect = openOrCreateDatabase("miBaseDatos", MODE_PRIVATE, null);
        listCon = (ListView)findViewById(R.id.listCon);
        try {
            sqldbConnect.execSQL("CREATE TABLE IF NOT EXISTS tblContactos(" +
                    "_id integer PRIMARY KEY autoincrement," +
                    "nombre text," +
                    "telefono text);");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        ///try {
            //Insertar valores
           // sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Alejandro','111-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Salazar','222-1111');");
           // sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Jose','333-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Angelica','444-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Eduardo','555-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Ruben','666-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Mabel','777-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Majo','888-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Javier','999-1111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Alan','123-111');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Pedro','123-222');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Kevin','123-333');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Lesly','123-444');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Vicky','123-555');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Edgar','123-666');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Rogelio','123-777');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Carlos','123-888');");
            //sqldbConnect.execSQL("INSERT INTO tblContactos(nombre,telefono) VALUES ('Fanny','123-999');");
        //} catch (SQLiteException e) {
         //   e.printStackTrace();
       // }
        //Consultar Valores, Creamos un cursor:
        c1 = sqldbConnect.rawQuery("SELECT * FROM tblContactos", null);
        adap = new Cursoradapter(this, c1);
        listCon.setAdapter(adap);
        //Responder al click
        listCon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Formularios.class);
                i.putExtra("id", id);
                startActivityForResult(i, RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT) {
            if (resultCode == RESULT_OK) {
                c1 = sqldbConnect.rawQuery("SELECT * FROM tblAmigo", null);
                adap = new Cursoradapter(this, c1);
                listCon.setAdapter(adap);
                Toast.makeText(getApplicationContext(), data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }

    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_principal, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
