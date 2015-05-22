package uy.com.antel.capacitacion.pruebacursobasico;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class Verificacion extends ActionBarActivity {

   ProgressBar pb;
    private Handler handler = new Handler();
    Bundle bundle;

    Intent result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);
        pb = (ProgressBar) findViewById(R.id.progressBar);



        result=new Intent();

        bundle=this.getIntent().getExtras();
        new Thread(new Runnable() {
            @Override
            public void run() {

                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(bundle.getString("usu").equalsIgnoreCase("USR1") && bundle.getString("pass").equals("PASS1")){
                            result.putExtra("login", true);
                            Verificacion.this.setResult(RESULT_OK, result);

                        }else{
                            result.putExtra("login", false);
                            Verificacion.this.setResult(RESULT_OK, result);

                        }
                        finish();
                    }
                }) ;




            }
        }).start();



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
