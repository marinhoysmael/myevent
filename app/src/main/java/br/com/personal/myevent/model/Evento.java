package br.com.personal.myevent.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by ysmael on 29/12/15.
 */
@DatabaseTable(tableName = "Evento")
public class Evento {

    public static String ID = "id";
    public static String NOME = "nome";
    public static String LOCAL = "local";
    public static String DATA = "data";
    public static String ENTRADA = "entrada";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String local;

    @DatabaseField(version = true, dataType = DataType.DATE_LONG, canBeNull = true)
    private Date data;

    @DatabaseField
    private double entrada;

    public Evento(){}

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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }
}
