package com.Unipar.trabalhopaulinho3bi_rafaelmiranda;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaração dos campos
    private EditText etNome, etEmail, etIdade, etDisciplina, etNota1, etNota2;
    private TextView tvErro, tvResumo;
    private Button btnEnviar, btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculação dos componentes do layout
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etIdade = findViewById(R.id.etIdade);
        etDisciplina = findViewById(R.id.etDisciplina);
        etNota1 = findViewById(R.id.etNota1);
        etNota2 = findViewById(R.id.etNota2);
        tvErro = findViewById(R.id.tvErro);
        tvResumo = findViewById(R.id.tvResumo);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnLimpar = findViewById(R.id.btnLimpar);

        // Ação para o botão "Enviar"
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarFormulario();
            }
        });

        // Ação para o botão "Limpar"
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparFormulario();
            }
        });
    }

    private void validarFormulario() {
        // Resetando mensagens de erro
        tvErro.setVisibility(View.GONE);
        tvResumo.setVisibility(View.GONE);

        // Captura dos valores inseridos
        String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();
        String idadeStr = etIdade.getText().toString();
        String disciplina = etDisciplina.getText().toString();
        String nota1Str = etNota1.getText().toString();
        String nota2Str = etNota2.getText().toString();

        // Verificando campos vazios
        if (TextUtils.isEmpty(nome)) {
            mostrarErro("O campo de nome está vazio");
            return;
        }
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mostrarErro("O email é inválido");
            return;
        }
        if (TextUtils.isEmpty(idadeStr)) {
            mostrarErro("O campo de idade está vazio");
            return;
        }
        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade <= 0) {
                mostrarErro("A idade deve ser um número positivo");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarErro("A idade deve ser um número válido");
            return;
        }

        if (TextUtils.isEmpty(disciplina)) {
            mostrarErro("O campo de disciplina está vazio");
            return;
        }
        if (TextUtils.isEmpty(nota1Str) || TextUtils.isEmpty(nota2Str)) {
            mostrarErro("As notas não podem estar vazias");
            return;
        }

        float nota1, nota2;
        try {
            nota1 = Float.parseFloat(nota1Str);
            nota2 = Float.parseFloat(nota2Str);
            if (nota1 < 0 || nota1 > 100 || nota2 < 0 || nota2 > 100) {
                mostrarErro("As notas devem estar entre 0 e 100");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarErro("As notas devem ser valores numéricos");
            return;
        }

        // Cálculo da média//
        float media = (nota1 + nota2) / 2;

        // Exibição do resumo//
        String status = media >= 60 ? "Aprovado" : "Reprovado";
        String resumo = String.format(
                "Nome: %s\nEmail: %s\nIdade: %d\nDisciplina: %s\nNotas: %.2f, %.2f\nMédia: %.2f\nStatus: %s",
                nome, email, idade, disciplina, nota1, nota2, media, status);

        tvResumo.setText(resumo);
        tvResumo.setVisibility(View.VISIBLE);
    }

    // Exibe a mensagem de erro//
    private void mostrarErro(String mensagem) {
        tvErro.setText(mensagem);
        tvErro.setVisibility(View.VISIBLE);
    }

    // Limpa todos os campos e esconde mensagens de erro e resumo//
    private void limparFormulario() {
        etNome.setText("");
        etEmail.setText("");
        etIdade.setText("");
        etDisciplina.setText("");
        etNota1.setText("");
        etNota2.setText("");
        tvErro.setVisibility(View.GONE);
        tvResumo.setVisibility(View.GONE);
    }



}