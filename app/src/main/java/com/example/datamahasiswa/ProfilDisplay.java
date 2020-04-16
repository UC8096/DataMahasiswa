package com.example.datamahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


public class ProfilDisplay extends AppCompatActivity {

    private DBHelper mydb;
    TextView namaa;
    TextView phonea;
    TextView emaila;
    TextView addressa;
    int id_To_Update = 0;

//    public void run(View view) {
//        if (nama.getText().toString().equals("") || phone.getText().toString().equals("") || email.getText().toString().equals("") || address.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), "Data Harus Diisi Semua !", Toast.LENGTH_LONG).show();
//        } else {
//            mydb.insertContact(nama.getText().toString(), phone.getText().toString(), email.getText().toString(), address.getText().toString());
//            Toast.makeText(getApplicationContext(),
//                    "Insert Data Berhasil", Toast.LENGTH_LONG).show();
//
//            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(i);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_display);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        namaa = (TextView) findViewById(R.id.ednamea);
        phonea = (TextView) findViewById(R.id.edphonea);
        emaila = (TextView) findViewById(R.id.edemaila);
        addressa = (TextView) findViewById(R.id.edaddressa);


        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String anam =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String aphon =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                String aemai =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_EMAIL));
                String aaddres =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_ADDRESS));
                if (!rs.isClosed()) {
                    rs.close();
                }
//                Button b = (Button) findViewById(R.id.save);
//                b.setVisibility(View.INVISIBLE);

                namaa.setText((CharSequence) anam);
                phonea.setText((CharSequence) aphon);
                emaila.setText((CharSequence) aemai);
                addressa.setText((CharSequence) aaddres);

            }
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            int Value = extras.getInt("id");
//            if (Value > 0) {
//                getMenuInflater().inflate(R.menu.menu_display, menu);
//            } else {
//                getMenuInflater().inflate(R.menu.menu_main, menu);
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////        return super.onOptionsItemSelected(item);
//
//        Bundle extras = getIntent().getExtras();
//
//        if (extras != null) {
//            int Value = extras.getInt("id");
//            if (Value > 0) {
//                switch (item.getItemId()) {
//                    case R.id.Delete_Contact:
////                        mydb.deleteContact(String.valueOf(Value));
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(i);
//
//                    case R.id.Edit_Contact:
////                        mydb.updateContact(String.valueOf(Value), nama.getText().toString(), phone.getText().toString(), email.getText().toString(), address.getText().toString());
//                        Intent ii = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(ii);
//                }
//            }
//        }
//        return true;
//    }
}
