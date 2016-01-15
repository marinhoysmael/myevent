package br.com.personal.myevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import br.com.personal.myevent.Service.UsuarioService;
import br.com.personal.myevent.model.Usuario;
import br.com.personal.myevent.util.UsuarioUtil;

public class AccountActivity extends AppCompatActivity {
    private AutoCompleteTextView nameView;
    private AutoCompleteTextView emailView;
    private EditText passwordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        nameView = (AutoCompleteTextView) findViewById(R.id.user_name);
        emailView = (AutoCompleteTextView) findViewById(R.id.user_email);
        passwordView = (EditText) findViewById(R.id.use_password);

        Button newUserButton = (Button) findViewById(R.id.new_user_button);

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaUser();
            }
        });
    }

    public void validaUser(){

        nameView.setError(null);
        emailView.setError(null);
        passwordView.setError(null);

        boolean error = false;
        View focusView = null;

        String name = nameView.getText().toString();
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        //Validar nome
        if (TextUtils.isEmpty(name)) {
            nameView.setError(getString(R.string.error_field_required));
            focusView = nameView;
            error = true;
        } else if (!UsuarioUtil.isNameValid(name)) {
            nameView.setError(getString(R.string.error_invalid_name));
            focusView = nameView;
            error = true;
        }

        //Validar email
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            error = true;
        } else if (!UsuarioUtil.isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            error = true;
        }

        //Validar Senha
        if(TextUtils.isEmpty(password)){
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            error = true;
        }else if(!UsuarioUtil.isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            error = true;
        }

        if (error) {
            focusView.requestFocus();
        } else {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNome(name);
            usuario.setSenha(password);
            usuario.setLogado(true);
            try {
                UsuarioService.getInstance(getApplicationContext()).create(usuario);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finalize();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Erro ao salvar Usuário", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
