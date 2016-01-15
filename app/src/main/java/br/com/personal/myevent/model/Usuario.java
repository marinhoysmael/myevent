package br.com.personal.myevent.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysmael on 29/12/15.
 */
@DatabaseTable(tableName = "Usuario")
public class Usuario {

    public static String ID = "id";
    public static String NOME = "nome";
    public static String EMAIL = "email";
    public static String SENHA = "senha";
    public static String LOGADO = "logado";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String email;

    @DatabaseField
    private String senha;

    @DatabaseField
    private boolean logado;

    public Usuario(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}


