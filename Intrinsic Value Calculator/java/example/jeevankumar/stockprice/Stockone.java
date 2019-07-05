package example.jeevankumar.stockprice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Stockone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockone);
       final EditText Price=(EditText)findViewById(R.id.Price);
        final EditText Eps=(EditText)findViewById(R.id.eps);
        final EditText Cashflow=(EditText)findViewById(R.id.cashflow);
        final EditText Growth=(EditText)findViewById(R.id.growth);
        final EditText G2=(EditText)findViewById(R.id.g2);
        final EditText Noofshares=(EditText)findViewById(R.id.noofshares);
        final Button b=(Button)findViewById(R.id.button3);
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                        double price=Double.parseDouble(Price.getText().toString());
                        double eps=Double.parseDouble(Eps.getText().toString());
                        double cashflow=Double.parseDouble(Cashflow.getText().toString());
                        double growth=Double.parseDouble(Growth.getText().toString())/100;
                        double g2=Double.parseDouble(G2.getText().toString())/100;
                        double noofshares=Double.parseDouble(Noofshares.getText().toString());
                        double j=(growth+1)/1.1000;
                        double subdcf=cashflow;
                        double dcf2=0;
                        for(int i=0;i<=10;i++){
                            subdcf*=j;
                            dcf2=subdcf;
                        }
                        double tv=cashflow*Math.pow((1+growth),10)*(1+g2)/(0.1-g2);
                        tv=tv/Math.pow(1.1,10);
                        double dcf=tv+dcf2;
                        final double intrinsic=dcf/noofshares;
                        final double onelakh=eps/price*100000;
                        Intent i=new Intent(Stockone.this,Stockop.class);
                        i.putExtra("intrin",intrinsic);
                        i.putExtra("one",onelakh);
                        startActivity(i);
                    }
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                    }}
                }

        );

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
                refreshing();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
    public void refreshing(){
        final EditText Price=(EditText)findViewById(R.id.Price);
        final EditText Eps=(EditText)findViewById(R.id.eps);
        final EditText Cashflow=(EditText)findViewById(R.id.cashflow);
        final EditText Growth=(EditText)findViewById(R.id.growth);
        final EditText G2=(EditText)findViewById(R.id.g2);
        final EditText Noofshares=(EditText)findViewById(R.id.noofshares);
        Price.setText("");
        Eps.setText("");
        Cashflow.setText("");
        Growth.setText("");G2.setText("");Noofshares.setText("");
    }
}
