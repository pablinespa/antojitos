package ucb.bo.edu.antojitosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class RestaurantActivity extends AppCompatActivity {

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        this.mToolBar = (Toolbar) findViewById(R.id.toolbar);

        if (this.mToolBar != null) {
            setSupportActionBar(this.mToolBar);
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_registeraction_register) {
            Toast.makeText(this, "Por favor contactese con nosotros para registrar su Restaurant. Gracias", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_about) {
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_terms) {
            Intent i = new Intent(this, TermActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_contact) {
            Intent i = new Intent(this, ContactActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_search) {
            Intent i = new Intent(this, SearchActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
