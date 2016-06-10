package dzumi.app.demo.demot3h.modules.user_interface.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import dzumi.app.demo.demot3h.R;

public class DemoOptionMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_option_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.mnInsert){
            Toast.makeText(DemoOptionMenu.this, "Insert", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.mnRefesh){
            Toast.makeText(DemoOptionMenu.this, "Refesh", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.mnSearch){
            Toast.makeText(DemoOptionMenu.this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
