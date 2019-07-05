package example.jeevankumar.stockprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class company_intrinsic extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference company_reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_intrinsic);
        firebaseDatabase=FirebaseDatabase.getInstance();
        company_reference=firebaseDatabase.getReference("Companies");
        Intent i=getIntent();
        String company_name=i.getStringExtra("company_name");
        final EditText Price=(EditText)findViewById(R.id.Price2);
        final EditText Eps=(EditText)findViewById(R.id.eps2);
        final EditText Cashflow=(EditText)findViewById(R.id.cashflow2);
        final EditText Growth=(EditText)findViewById(R.id.growth2);
        final EditText G2=(EditText)findViewById(R.id.g22);
        final EditText Noofshares=(EditText)findViewById(R.id.noofshares2);
        final Button b=(Button)findViewById(R.id.button_get_intrinsic_values);
        company_reference.child(company_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Price.setText(dataSnapshot.child("share_price").getValue().toString());
                Eps.setText(dataSnapshot.child("eps").getValue().toString());
                Cashflow.setText(dataSnapshot.child("cashflow").getValue().toString());
                Growth.setText(dataSnapshot.child("annual_growth").getValue().toString());
                G2.setText(dataSnapshot.child("later_growth").getValue().toString());
                Noofshares.setText(dataSnapshot.child("total_shares").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                            Intent i=new Intent(company_intrinsic.this,Stockop.class);
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
    }

