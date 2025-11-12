package com.example.dcextiv252;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.dcextiv252.R;
import com.example.dcextiv252.TempoDorActivity;

public class DorActivity extends AppCompatActivity {

    private String convenioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dor);

        convenioSelecionado = getIntent().getStringExtra("CONVENIO");

        Button btnVoltar = findViewById(R.id.btnVoltarDor);
        btnVoltar.setOnClickListener(v -> {
            finish();
        });


        GridLayout gridDor = findViewById(R.id.gridDor);
        View.OnClickListener regiaoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regiaoDor = (String) v.getTag();

                Intent intent = new Intent(DorActivity.this, TempoDorActivity.class);

                intent.putExtra("CONVENIO", convenioSelecionado);
                intent.putExtra("REGIAO_DOR", regiaoDor);

                startActivity(intent);
            }
        };

        for (int i = 0; i < gridDor.getChildCount(); i++) {
            View child = gridDor.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(regiaoClickListener);
            }
        }
    }
}