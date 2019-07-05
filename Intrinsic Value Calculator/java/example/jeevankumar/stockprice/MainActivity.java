package example.jeevankumar.stockprice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {
    private String[] titles;
    private ListView drawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titles=getResources().getStringArray(R.array.titles);
        drawerList=(ListView)findViewById(R.id.drawer);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.refresh:
                final EditText Price=(EditText)findViewById(R.id.Price);
                final EditText Eps=(EditText)findViewById(R.id.eps);
                final EditText Cashflow=(EditText)findViewById(R.id.cashflow);
                final EditText Growth=(EditText)findViewById(R.id.growth);
                final EditText G2=(EditText)findViewById(R.id.g2);
                final EditText Noofshares=(EditText)findViewById(R.id.noofshares);
                Price.setText(0);
                Eps.setText(0);
                Cashflow.setText(0);
                Growth.setText(0);
                G2.setText(0);
                Noofshares.setText(0);
                return true;
                default:
                    return super.onOptionsItemSelected(menuItem);
        }
    }
    public void onstockclick(View view){
        Intent i=new Intent(".Stockone");
        startActivity(i);
    }
    public void oncompanyclick(View view){
        Button imageButton=(Button)findViewById(R.id.button2);
        startActivity(new Intent(MainActivity.this,Company.class));
    }
}
