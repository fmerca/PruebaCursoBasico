package uy.com.antel.capacitacion.pruebacursobasico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    Button btnLogin;
    EditText txtUsu, txtPass;
    final private static Integer CODE = 1;
    Toast toast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        txtUsu = (EditText)findViewById(R.id.txtUsu);
        txtPass = (EditText)findViewById(R.id.txtPass);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openIntent= new Intent(LoginActivity.this, Verificacion.class);
                openIntent.putExtra("usu", txtUsu.getText().toString());
                openIntent.putExtra("pass", txtPass.getText().toString());
                startActivityForResult(openIntent, CODE);
            }
        });




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE && resultCode == RESULT_OK) {
            Boolean value_result = data.getBooleanExtra("login", false);
            if (value_result) {
                Intent openCargar = new Intent(LoginActivity.this, CompartirArchivo.class);
                Bundle bundle = LoginActivity.this.getIntent().getExtras();
                String img_uri = bundle.getString("uri");
                openCargar.putExtra("uri", img_uri);
                startActivity(openCargar);
                finish();
            } else {
                toast = Toast.makeText(this, R.string.bad_usr, Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
