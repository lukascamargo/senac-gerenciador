package com.example.gerenciadorv3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.gerenciadorv3.bdHelper.ColaboradoresBd;
import com.example.gerenciadorv3.model.Colaboradores;

public class FormularioColaboradores extends AppCompatActivity{

    EditText NomeColaborador, emailColaborador, telColaborador, IdadeColaborador;
    RadioGroup radioCargo;
    Button btnCadastrar;
    Colaboradores editarColaborador, colaborador;
    ColaboradoresBd bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colaborador = new Colaboradores();
        bdHelper = new ColaboradoresBd(FormularioColaboradores.this);

        Intent intent = getIntent();
        editarColaborador = (Colaboradores) intent.getSerializableExtra("colaborador-escolhido");

        NomeColaborador = (EditText) findViewById(R.id.NomeColaborador);
        emailColaborador = (EditText) findViewById(R.id.emailColaborador);
        telColaborador = (EditText) findViewById(R.id.telColaborador);
        IdadeColaborador = (EditText) findViewById(R.id.IdadeColaborador);
        radioCargo = (RadioGroup) findViewById(R.id.radioCargo);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        if (editarColaborador != null){
            btnCadastrar.setText("Modificar");
            NomeColaborador.setText(editarColaborador.getNomeColaborador());
            emailColaborador.setText(editarColaborador.getEmailColaborador());
            telColaborador.setText(editarColaborador.getTelefoneColaborador());
            IdadeColaborador.setText(editarColaborador.getIdadeColaborador());
            colaborador.setId(editarColaborador.getId());

        } else {
            btnCadastrar.setText("Cadastrar");
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colaborador.setNomeColaborador(NomeColaborador.getText().toString());
                colaborador.setEmailColaborador(emailColaborador.getText().toString());
                colaborador.setTelefoneColaborador(Integer.parseInt(telColaborador.getText().toString()));
                colaborador.setIdadeColaborador(Integer.parseInt(IdadeColaborador.getText().toString()));
                colaborador.setCargoColaborador(String.valueOf(radioCargo.getCheckedRadioButtonId()));

                if (btnCadastrar.getText().toString().equals("Cadastrar")){
                    bdHelper.salvarColaborador(colaborador);
                    bdHelper.close();
                } else {
                    bdHelper.alterarColaborador(colaborador);
                    bdHelper.close();
                }
            }
        });

    }
}
