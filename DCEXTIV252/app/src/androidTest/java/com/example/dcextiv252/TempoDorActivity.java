package com.example.dcextiv252; // Mude para o seu pacote

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout; // Importe este
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dcextiv252.R;

public class TempoDorActivity extends AppCompatActivity {

    private String convenioSelecionado;
    private String regiaoDorSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempo_dor);

        // 1. Receber os dados da tela anterior (DorActivity)
        convenioSelecionado = getIntent().getStringExtra("CONVENIO");
        regiaoDorSelecionada = getIntent().getStringExtra("REGIAO_DOR");

        Button btnVoltar = findViewById(R.id.btnVoltarTempoDor);
        btnVoltar.setOnClickListener(v -> {
            finish();
        });

        GridLayout gridTempoDor = findViewById(R.id.gridTempoDor);
        View.OnClickListener tempoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempoDor = (String) v.getTag();

                Intent intent = new Intent(TempoDorActivity.this, ResumoActivity.class);
                intent.putExtra("CONVENIO", convenioSelecionado);
                intent.putExtra("REGIAO_DOR", regiaoDorSelecionada);
                intent.putExtra("TEMPO_DOR", tempoDor);

                startActivity(intent);
            }
        };

        for (int i = 0; i < gridTempoDor.getChildCount(); i++) {
            View child = gridTempoDor.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(tempoClickListener);
            }
        }
    }
}