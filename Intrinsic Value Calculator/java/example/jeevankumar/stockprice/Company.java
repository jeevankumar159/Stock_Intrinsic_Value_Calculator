package example.jeevankumar.stockprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

public class Company extends AppCompatActivity {

    ArrayList<String> company_array_list=new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference company_reference;
    Spinner company_spinner;String company_name;Button company_intrinsic_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company2);
        company_intrinsic_value=(Button)findViewById(R.id.button5);
        firebaseDatabase=FirebaseDatabase.getInstance();
        company_reference=firebaseDatabase.getReference("Companies");
        company_spinner=(Spinner)findViewById(R.id.spinner);
        final ArrayAdapter<String> company_adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,company_array_list);
        company_spinner.setAdapter(company_adapter);
        company_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    company_array_list.add(data.getKey().toString());
                    company_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        company_intrinsic_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                company_name=company_spinner.getSelectedItem().toString();
                Intent i=new Intent(Company.this,company_intrinsic.class);
                i.putExtra("company_name",company_name);
                startActivity(i);
            }
        });
    }
}
