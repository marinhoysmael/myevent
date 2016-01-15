package br.com.personal.myevent.ServiceDao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

import br.com.personal.myevent.Service.UsuarioService;
import br.com.personal.myevent.model.Usuario;

/**
 * Created by ysmael on 30/12/15.
 */
public class UsuarioServiceDao extends UsuarioService {

    public UsuarioServiceDao(Context context) {
        super(context);
    }


    @Override
    public Usuario buscarPorEmail(String email) throws SQLException {
        Dao<Usuario, Long> usuarioDao =  getDataBaseHelper().getUsuarioDao();

        QueryBuilder usuarioQB = usuarioDao.queryBuilder();

        usuarioQB.where().like(Usuario.EMAIL, email);
        usuarioQB.limit(1l);

        Usuario usuario = usuarioDao.queryForFirst(usuarioQB.prepare());
        return usuario;
    }


    @Override
    public Usuario buscarUltimoUsuario() throws SQLException {
        Dao<Usuario, Long> usuarioDao =  getDataBaseHelper().getUsuarioDao();

        QueryBuilder usuarioQB = usuarioDao.queryBuilder();
        usuarioQB.limit(1l);

        Usuario usuario = usuarioDao.queryForFirst(usuarioQB.prepare());

        return usuario;
    }

    @Override
    public void create(Usuario usuario) throws SQLException{
        Usuario usuarioSalvo = buscarUltimoUsuario();
        if(usuarioSalvo != null){
            usuario.setId(usuarioSalvo.getId());
        }
        getDataBaseHelper().getUsuarioDao().createOrUpdate(usuario);
    }

    @Override
    public void loginUser() throws SQLException {
        Usuario usuario = buscarUltimoUsuario();
        usuario.setLogado(true);

        getDataBaseHelper().getUsuarioDao().update(usuario);
    }

    @Override
    public void logoutUser() throws SQLException {
        Usuario usuario = buscarUltimoUsuario();
        usuario.setLogado(false);

        getDataBaseHelper().getUsuarioDao().update(usuario);
    }
}
