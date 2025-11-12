package com.example.dcextiv252; // Mude para o seu pacote

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout; // Importe este

import com.example.dcextiv252.R;

public class ConvenioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenio);

        Button btnVoltar = findViewById(R.id.btnVoltarConvenio);
        btnVoltar.setOnClickListener(v -> {
            finish();
        });

        // Configura o clique para todos os botões de convênio
        GridLayout grid = findViewById(R.id.gridConvenios);
        View.OnClickListener convenioClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String convenioSelecionado = (String) v.getTag();

                Intent intent = new Intent(ConvenioActivity.this, com.seupacote.autoatendimento.DorActivity.class);

                intent.putExtra("CONVENIO", convenioSelecionado);

                startActivity(intent);
            }
        };

        for (int i = 0; i < grid.getChildCount(); i++) {
            View child = grid.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(convenioClickListener);
            }
        }
    }
}