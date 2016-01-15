package br.com.personal.myevent.Service;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import br.com.personal.myevent.percistence.DataBaseHelper;

/**
 * Created by ysmael on 30/12/15.
 */
public abstract class AppService {
    private DataBaseHelper databaseHelper;
    private Context context;

    public AppService(Context context){
        if(databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        }

        if( ! databaseHelper.isOpen()){
            databaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        }
        this.context = context;
    }

    protected DataBaseHelper getDataBaseHelper() {
        return databaseHelper;
    }

    protected Context getContext() {
        return context;
    }

}
