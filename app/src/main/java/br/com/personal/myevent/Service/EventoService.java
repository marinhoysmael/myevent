package br.com.personal.myevent.Service;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import br.com.personal.myevent.ServiceDao.EventoServiceDao;
import br.com.personal.myevent.model.Evento;

/**
 * Created by ysmael on 31/12/15.
 */
public abstract class EventoService extends AppService{
    private static EventoService eventoService;

    public EventoService(Context context){
        super(context);
    }

    public static EventoService getStance(Context context){
        if(eventoService == null)
            eventoService = new EventoServiceDao(context);

        return eventoService;
    }

    public abstract List<Evento> listarEventos() throws SQLException;

    public abstract void create(Evento evento) throws SQLException;

    public abstract void update(Evento evento) throws SQLException;

    public abstract void updateBuild(Evento evento) throws SQLException;

    public abstract void deleteById(long id) throws SQLException;

    public abstract Evento findById(long id) throws SQLException;
}
