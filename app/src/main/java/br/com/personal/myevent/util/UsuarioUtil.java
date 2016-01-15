package br.com.personal.myevent.util;

/**
 * Created by ysmael on 30/12/15.
 */
public class UsuarioUtil {

    public static boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

    public static boolean isNameValid(String name){
        return name.contains(" ");
    }
}
