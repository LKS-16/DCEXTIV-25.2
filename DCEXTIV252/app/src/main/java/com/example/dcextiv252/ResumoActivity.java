package com.example.dcextiv252;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResumoActivity extends AppCompatActivity {

    private String convenio;
    private String regiaoDor;
    private String tempoDor;

    private TextView tvValorConvenio;
    private TextView tvValorRegiaoDor;
    private TextView tvValorTempoDor;
    private Button btnEditarResumo;
    private Button btnConcluirResumo;
    private Button btnVoltarResumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        convenio = getIntent().getStringExtra("CONVENIO");
        regiaoDor = getIntent().getStringExtra("REGIAO_DOR");
        tempoDor = getIntent().getStringExtra("TEMPO_DOR");

        tvValorConvenio = findViewById(R.id.tvValorConvenio);
        tvValorRegiaoDor = findViewById(R.id.tvValorRegiaoDor);
        tvValorTempoDor = findViewById(R.id.tvValorTempoDor);
        btnEditarResumo = findViewById(R.id.btnEditarResumo);
        btnConcluirResumo = findViewById(R.id.btnConcluirResumo);
        btnVoltarResumo = findViewById(R.id.btnVoltarResumo);

        tvValorConvenio.setText(convenio);
        tvValorRegiaoDor.setText(regiaoDor);
        tvValorTempoDor.setText(tempoDor);

        btnEditarResumo.setOnClickListener(v -> {
            Intent intent = new Intent(ResumoActivity.this, ConvenioActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        btnConcluirResumo.setOnClickListener(v -> {
            Intent intent = new Intent(ResumoActivity.this, ComprovanteActivity.class);
            intent.putExtra("CONVENIO", convenio);
            intent.putExtra("REGIAO_DOR", regiaoDor);
            intent.putExtra("TEMPO_DOR", tempoDor);
            startActivity(intent);
        });

        btnVoltarResumo.setOnClickListener(v -> {
            finish();
        });
    }
}