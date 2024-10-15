package com.example.based_on_dialogs_assig1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //declaration
    Button filterbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        filterbtn= findViewById(R.id.filterbtn);

        // here we will set a OnClicklistener on the Filter button

        filterbtn.setOnClickListener(view -> showFilterDialog());
    }

    private void showFilterDialog() {
        //Inflate the dialog layout  //we can also use setContentView
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.filter_dialog, null);

        //build the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);
        final AlertDialog dialog=dialogBuilder.create();

        dialog.show();

        //Here we will get references to UI elements in the dialog

        SeekBar seekBar = dialogView.findViewById(R.id.seekBar);
        TextView txtView= dialogView.findViewById(R.id.txtView);
        CheckBox checkBox1 = dialogView.findViewById(R.id.checkBox1);
        CheckBox checkBox2 = dialogView.findViewById(R.id.checkBox2);
        CheckBox checkBox3 = dialogView.findViewById(R.id.checkBox3);
        Button ok_btn = dialogView.findViewById(R.id.ok_btn);

        //here we will set the behaviour for 1 BHK,2 BHK,3BHK
        //we will apply checkbox click listeners to allow only one checkbox to be checked at a time
        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                seekBar.setProgress(30);
                txtView.setText("Price Range: 30");
                Toast.makeText(this, "For 1 BHK the price Range is 30", Toast.LENGTH_LONG).show();

            }
        });

        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox1.setChecked(false);
                checkBox3.setChecked(false);
                seekBar.setProgress(60);
                txtView.setText("Price Range: 60");
                Toast.makeText(this, "For 2 BHK the price Range is 60", Toast.LENGTH_LONG).show();

            }
        });

        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                seekBar.setProgress(90);
                txtView.setText("Price Range: 90");
                Toast.makeText(this, "For 3 BHK the price Range is 90", Toast.LENGTH_LONG).show();

            }
        });

        //ok button click listener
        ok_btn.setOnClickListener(v -> {

            dialog.dismiss();
        });
    }
}