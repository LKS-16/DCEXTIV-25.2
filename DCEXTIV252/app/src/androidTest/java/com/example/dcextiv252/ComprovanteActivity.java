package com.example.dcextiv252;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

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

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        String dataHoraFormatada = agora.format(formatter);

        String detalhesHtml = "<b>Data/Hora:</b> " + dataHoraFormatada + "<br><br>"
                + "<b style='font-size: 1.1em;'>Detalhes do Atendimento:</b><br>"
                + "<b>Convênio:</b> " + convenio + "<br>"
                + "<b>Região da Dor:</b> " + regiaoDor + "<br>"
                + "<b>Tempo da Dor:</b> " + tempoDor + "<br><br>"
                + "Prezado(a) paciente, seu atendimento foi registrado e você será encaminhado(a) ao profissional responsável.";

        tvDetalhesComprovante.setText(Html.fromHtml(detalhesHtml, Html.FROM_HTML_MODE_LEGACY));


        btnFecharComprovante.setOnClickListener(v -> {
            Intent intent = new Intent(ComprovanteActivity.this, MainActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}