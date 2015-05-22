package uy.com.antel.capacitacion.pruebacursobasico;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent share_image = this.getIntent();
        Cursor cursor;
        Uri image_Uri = share_image.getParcelableExtra(Intent.EXTRA_STREAM);
        Intent openIntent = new Intent(MainActivity.this, LoginActivity.class);
        toast = Toast.makeText(MainActivity.this, "Ninguna Imagen Seleccionada",
                Toast.LENGTH_SHORT);
        if (image_Uri==null) {
            this.finish();
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
        String[] imgs = {MediaStore.Images.Media.DATA};
        cursor = this.getContentResolver().query(image_Uri, imgs, null, null, null);
        cursor.moveToFirst();
        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));




//            openIntent.setAction(Intent.ACTION_SEND);
//            openIntent.putExtra(Intent.EXTRA_STREAM,image_Uri);
            openIntent.putExtra("uri", image_Uri.toString());
            startActivity(openIntent);
            finish();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
