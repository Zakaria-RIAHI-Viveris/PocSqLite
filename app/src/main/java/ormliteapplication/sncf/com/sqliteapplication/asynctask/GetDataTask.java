package ormliteapplication.sncf.com.sqliteapplication.asynctask;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ormliteapplication.sncf.com.sqliteapplication.db.dao.UserDao;
import ormliteapplication.sncf.com.sqliteapplication.model.User;

/**
 * Created by PZII11871 on 09/02/2017.
 */

public class GetDataTask extends AsyncTask<Void, Integer, List<User>> {

    private static final String TAG = "RecupererDonnesTask";
    private ProgressDialog progressDialog;
    private String condition;
    private Application application;

    public GetDataTask(ProgressDialog progressDialog, String condition, Application application) {
        this.progressDialog = progressDialog;
        this.condition = condition;
        this.application = application;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    protected List<User> doInBackground(Void... params) {
        return getUtilieursCondition();
    }

    @Override
    protected void onPostExecute(List<User> result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        afficherResultat();
    }


    private List<User> getUtilieursCondition() {
        List<User> res;
        UserDao userDao = new UserDao(application.getApplicationContext());
        userDao.open();
        long before = System.currentTimeMillis();
        res = userDao.getUtilisateursParNumCp(condition);
        long after = System.currentTimeMillis();
        long duration = after - before;
        Log.i(TAG, "RecupererDonnesTask duration " + duration);
        userDao.close();
        return res;
    }

    private void afficherResultat() {
        Toast.makeText(application, "RecupererDonnesTask Success", Toast.LENGTH_SHORT).show();
    }
}
