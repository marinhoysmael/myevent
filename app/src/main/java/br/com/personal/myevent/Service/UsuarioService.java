package br.com.personal.myevent.Service;

import android.content.Context;

import java.sql.SQLException;

import br.com.personal.myevent.exception.PasswordInvalidException;
import br.com.personal.myevent.exception.UserNotFoundException;
import br.com.personal.myevent.ServiceDao.UsuarioServiceDao;
import br.com.personal.myevent.model.Usuario;

/**
 * Created by ysmael on 30/12/15.
 */
public abstract class UsuarioService extends AppService{
    private static UsuarioService usuarioService = null;

    protected UsuarioService(Context context){
        super(context);
    }

    public static UsuarioService getInstance(Context context){

        if(usuarioService == null){
            usuarioService = new UsuarioServiceDao(context);
        }
        return usuarioService;
    }

    public void verificarLogin(String email, String senha) throws SQLException, PasswordInvalidException, UserNotFoundException {
        Usuario usuario = buscarPorEmail(email);
        if(usuario != null){
            if(!usuario.getSenha().equals(senha)){
                //Erro, senha não corresponde ao email
                throw new PasswordInvalidException();
            }
        }else{
            //ERRO, usuario não encontrado
            throw new UserNotFoundException();
        }
    }


    public abstract Usuario buscarPorEmail(String email) throws SQLException;
    public abstract Usuario buscarUltimoUsuario() throws SQLException;
    public abstract void create(Usuario usuario) throws SQLException;
    public  abstract void loginUser() throws SQLException;
    public  abstract void logoutUser() throws SQLException;
}
