package br.com.personal.myevent.percistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.personal.myevent.model.Evento;
import br.com.personal.myevent.model.Usuario;

/**
 * Created by ysmael on 29/12/15.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "event.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    private Dao<Evento, Long> eventoDao = null;
    private Dao<Usuario, Long> usuarioDao = null;


    private ConnectionSource connectionSource = null;


    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Evento.class);
            TableUtils.createTableIfNotExists(connectionSource, Usuario.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            connectionSource = super.getConnectionSource();
        }
        return connectionSource;
    }
    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
    }

    public Dao<Evento, Long> getEventoDao() throws SQLException{
        if(eventoDao == null)
            eventoDao = getDao(Evento.class);
        return eventoDao;
    }

    public Dao<Usuario, Long> getUsuarioDao()throws  SQLException{
        if(usuarioDao == null)
            usuarioDao = getDao(Usuario.class);
        return usuarioDao;
    }
}
