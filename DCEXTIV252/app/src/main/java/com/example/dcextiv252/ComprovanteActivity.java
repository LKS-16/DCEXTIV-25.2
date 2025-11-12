package com.example.dcextiv252;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
import com.example.dcextiv252.MainActivity;
import com.example.dcextiv252.R;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


import java.util.Random; // Este provavelmente já estava lá

public class ComprovanteActivity extends AppCompatActivity {

    private String convenio;
    private String regiaoDor;
    private String tempoDor;

    private TextView tvDetalhesComprovante;
    private TextView tvSenhaValor;
    private Button btnFecharComprovante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        convenio = getIntent().getStringExtra("CONVENIO");
        regiaoDor = getIntent().getStringExtra("REGIAO_DOR");
        tempoDor = getIntent().getStringExtra("TEMPO_DOR");

        tvDetalhesComprovante = findViewById(R.id.tvDetalhesComprovante);
        tvSenhaValor = findViewById(R.id.tvSenhaValor);
        btnFecharComprovante = findViewById(R.id.btnFecharComprovante);

        int numeroSenha = new Random().nextInt(999) + 1;
        String senhaAtendimento = String.format(Locale.US, "A-%03d", numeroSenha);
        tvSenhaValor.setText(senhaAtendimento);

        LocalDateTime agora = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            agora = LocalDateTime.now();
        }
        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        }
        String dataHoraFormatada = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dataHoraFormatada = agora.format(formatter);
        }

        String detalhesHtml = "<b>Data/Hora:</b> " + dataHoraFormatada + "<br><br>"
                + "<b style='font-size: 1.1em;'>Detalhes do Atendimento:</b><br>"
                + "<b>Convênio:</b> " + convenio + "<br>"
                + "<b>Região da Dor:</b> " + regiaoDor + "<br>"
                + "<b>Tempo da Dor:</b> " + tempoDor + "<br><br>"
                + "Prezado(a) paciente, seu atendimento foi registrado e você será encaminhado(a) ao profissional responsável.";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvDetalhesComprovante.setText(Html.fromHtml(detalhesHtml, Html.FROM_HTML_MODE_LEGACY));
        }

        btnFecharComprovante.setOnClickListener(v -> {
            Intent intent = new Intent(ComprovanteActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}