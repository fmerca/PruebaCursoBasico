package uy.com.antel.capacitacion.pruebacursobasico;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CompartirArchivo extends ActionBarActivity {

    Spinner spinnerCarpetas;
    TextView archivo;
    Button cargar;
    Toast toast;
    String carpetaSeleccionada;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir_archivo);
        bundle = CompartirArchivo.this.getIntent().getExtras();
        String[] carpetas = bundle.getStringArray("carpetas");
        spinnerCarpetas = (Spinner)findViewById(R.id.spinner);
        archivo = (TextView)findViewById(R.id.txtArchivo);
        archivo.setText(archivo.getText()+ bundle.getString("uri"));
        cargar = (Button)findViewById(R.id.btnCargar);
        AdaptadorPersonalizado aad = new AdaptadorPersonalizado(this,R.layout.adapta_spinner,carpetas);
        spinnerCarpetas.setAdapter(aad);

        spinnerCarpetas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCarpetas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

               carpetaSeleccionada= parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
// Método sin uso para Spinner se puede dejar vacio
            }
        });
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompartirArchivo.this, Upload.class);
                intent.putExtra("carpeta", carpetaSeleccionada);
                intent.putExtra("uri",  bundle.getString("uri").toString());
                startService(intent);


            }
        });

    }

    public class AdaptadorPersonalizado extends ArrayAdapter<String> {
        public AdaptadorPersonalizado(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
// Igual presentación en el menú que en reposo
            return getView(position, convertView, parent);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String[] carpetas = getResources().getStringArray(R.array.carpetas);


            LayoutInflater inflador = getLayoutInflater();
            View vista = inflador.inflate(R.layout.adapta_spinner, parent, false);
            ImageView iv = (ImageView) vista.findViewById(R.id.dibujito);
            //iv.setImageResource(recursoImagen[position]);
            TextView tv = (TextView) vista.findViewById(R.id.textito);
            tv.setText(carpetas[position]);
            return vista;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compartir_archivo, menu);
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
