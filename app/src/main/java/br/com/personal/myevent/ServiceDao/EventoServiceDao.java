package br.com.personal.myevent.ServiceDao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import br.com.personal.myevent.Service.EventoService;
import br.com.personal.myevent.model.Evento;

/**
 * Created by ysmael on 31/12/15.
 */
public class EventoServiceDao extends EventoService{

    public EventoServiceDao(Context context){
        super(context);
    }

    @Override
    public List<Evento> listarEventos() throws SQLException {
        Dao<Evento, Long> eventoDao = getDataBaseHelper().getEventoDao();

        QueryBuilder eventoQB = eventoDao.queryBuilder();

        return eventoQB.query();
    }

    @Override
    public void create(Evento evento) throws SQLException {
        if(evento != null)
            getDataBaseHelper().getEventoDao().createOrUpdate(evento);
    }

    @Override
    public void update(Evento evento) throws SQLException {
        if(evento != null && evento.getId() > 0){
            getDataBaseHelper().getEventoDao().update(evento);
        }
    }

    public void updateBuild(Evento evento) throws SQLException{
        UpdateBuilder<Evento, Long> updateBuilder = getDataBaseHelper().getEventoDao().updateBuilder();

        if(evento.getId() > 0) {

            updateBuilder.updateColumnValue(Evento.DATA, evento.getData());
            updateBuilder.updateColumnValue(Evento.ENTRADA, evento.getEntrada());
            updateBuilder.updateColumnValue(Evento.LOCAL, evento.getLocal());
            updateBuilder.updateColumnValue(Evento.NOME, evento.getNome());



            updateBuilder.where().eq(Evento.ID, evento.getId());


            updateBuilder.update();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        getDataBaseHelper().getEventoDao().deleteById(id);

    }

    @Override
    public Evento findById(long id) throws SQLException {
        return null;
    }
}
