package com.example.myalarma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    int hora1, minuto1;
    Button aceptar;
    EditText  hora;
    EditText titulo;
    Button bhora;
    CheckBox d,l,ma,mi,j,vi,s;

    ArrayList<Integer> days=new ArrayList<Integer>();

RadioButton on, off;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aceptar=findViewById(R.id.aceptar);
        hora=findViewById(R.id.hora);

        titulo=findViewById(R.id.titulo);
        bhora=(Button)findViewById(R.id.bhora);
        bhora.setOnClickListener(this);
        radioGroup = findViewById(R.id.radioGroup);
        d= findViewById(R.id.domingo);
        l=findViewById(R.id.lunes);
        ma=findViewById(R.id.martes);
        mi=findViewById(R.id.miercoles);
        j=findViewById(R.id.jueves);
        vi=findViewById(R.id.viernes);
        s=findViewById(R.id.sabado);

        on=findViewById(R.id.onv);
        off=findViewById(R.id.offv);





        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l.isChecked()){
                    days.add(Calendar.MONDAY);
                }
            }
        });

        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ma.isChecked()){
                    days.add(Calendar.TUESDAY);
                }
            }
        });
        mi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mi.isChecked()){
                    days.add(Calendar.WEDNESDAY);
                }
            }
        });
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(j.isChecked()){
                    days.add(Calendar.THURSDAY);
                }
            }
        });
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vi.isChecked()){
                    days.add(Calendar.FRIDAY);
                }
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.isChecked()){
                    days.add(Calendar.SATURDAY);
                }
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (d.isChecked()){
                    days.add(Calendar.SUNDAY);
                }
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                establecerAlrma(titulo.getText().toString(), hora1, minuto1);
                days.clear();

            }
        });
    }


    @Override
    public void onClick(View v) {


        final Calendar c= Calendar.getInstance();
        hora1=c.get(Calendar.HOUR_OF_DAY);
        minuto1=c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
           minuto1=minute;
           hora1=hourOfDay;
                hora.setText(hourOfDay+":"+minute);


            }
        },hora1,minuto1,false);
        timePickerDialog.show();

    }


    public void establecerAlrma (String titulo, int hora, int minuto) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, titulo)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                .putExtra(AlarmClock.EXTRA_DAYS, minuto);


        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.onv:
                intent.putExtra(AlarmClock.EXTRA_VIBRATE, true);
                break;
            case R.id.offv:
                intent.putExtra(AlarmClock.EXTRA_VIBRATE, false);
                break;



        }


        intent.putExtra(AlarmClock.EXTRA_DAYS, days);




if (intent.resolveActivity(getPackageManager())!=null){
    startActivity(intent);
}





    }


}
