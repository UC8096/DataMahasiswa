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


public class DisplayMhs extends AppCompatActivity {

    private DBHelper mydb;
    EditText nomhs;
    EditText phone;
    EditText nama;
    int id_To_Update = 0;

    public void run(View view) {
        if (nomhs.getText().toString().equals("") || nama.getText().toString().equals("") || phone.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Data Harus Diisi Semua !", Toast.LENGTH_LONG).show();
        } else {
            mydb.insertContact(nomhs.getText().toString(), nama.getText().toString(), phone.getText().toString());
            Toast.makeText(getApplicationContext(),
                    "Insert Data Berhasil", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_mhs);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        nomhs = (EditText) findViewById(R.id.editTextNim);
        nama = (EditText) findViewById(R.id.editTextName);
        phone = (EditText) findViewById(R.id.editTextPhone);

        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String no =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NIM));
                String nam =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String phon =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);
                nomhs.setText((CharSequence) no);
//                nomhs.setFocusable(false);
//                nomhs.setClickable(false);
                nama.setText((CharSequence) nam);
//                nama.setFocusable(false);
//                nama.setClickable(false);
                phone.setText((CharSequence) phon);
//                phone.setFocusable(false);
//                phone.setClickable(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.menu_display, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                switch (item.getItemId()) {
                    case R.id.Delete_Contact:
                        mydb.deleteContact(String.valueOf(Value));
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);

                    case R.id.Edit_Contact:
                        mydb.updateContact(String.valueOf(Value), nomhs.getText().toString(), nama.getText().toString(), phone.getText().toString());
                        Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(ii);
                }
            }
        }
        return true;
    }
}
