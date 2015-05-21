package uy.com.antel.capacitacion.pruebacursobasico;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class Verificacion extends ActionBarActivity {

    private int progressStatus = 0;
    ProgressBar pb;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            public void run() {

                handler.post(new Runnable() {
                    public void run() {

                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                            Thread.sleep(5000);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        }).start();


        Intent result=new Intent();
        Intent openIntent = new Intent(Verificacion.this, CompartirArchivo.class);
        Bundle bundle=this.getIntent().getExtras();
        if(bundle.getString("usu").equalsIgnoreCase("USR1") && bundle.getString("pass").equals("PASS1")){
            openIntent.putExtra("uri", bundle.getString("uri"));

            startActivity(openIntent);
        }else{
            result.putExtra("login", false);
            this.setResult(RESULT_OK, result);
            finish();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_verificacion, menu);
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
