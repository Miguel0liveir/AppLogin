package br.ulbra.applogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {
    EditText edNome, edUser, edPass1, edPass2;
    Button btSalvar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        db = new DBHelper(this);

        edNome = findViewById(R.id.edNome);
        edUser = findViewById(R.id.edUser);
        edPass1 = findViewById(R.id.edPass1);
        edPass2 = findViewById(R.id.edPass2);
        btSalvar = findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edNome.getText().toString();
                String userName = edUser.getText().toString();
                String pas1 = edPass1.getText().toString();
                String pas2 = edPass2.getText().toString();

                if (nome.equals("")) {
                    Toast.makeText(RegistrarActivity.this, "❌ Insira o NOME COMPLETO", Toast.LENGTH_SHORT).show();
                } else if (userName.equals("")) {
                    Toast.makeText(RegistrarActivity.this, "❌ Insira o LOGIN DO USUÁRIO", Toast.LENGTH_SHORT).show();
                } else if (pas1.equals("") || pas2.equals("")) {
                    Toast.makeText(RegistrarActivity.this, "❌ Insira a SENHA DO USUÁRIO", Toast.LENGTH_SHORT).show();
                } else if (!pas1.equals(pas2)) {
                    Toast.makeText(RegistrarActivity.this, "❌ As senhas não correspondem!", Toast.LENGTH_SHORT).show();
                } else if (pas1.length() < 6) {
                    Toast.makeText(RegistrarActivity.this, "❌ Senha deve ter pelo menos 6 caracteres!", Toast.LENGTH_SHORT).show();
                } else {
                    long res = db.criarUtilizador(userName, pas1);
                    if (res > 0) {
                        Toast.makeText(RegistrarActivity.this, "✅ Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegistrarActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(RegistrarActivity.this, "❌ Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
