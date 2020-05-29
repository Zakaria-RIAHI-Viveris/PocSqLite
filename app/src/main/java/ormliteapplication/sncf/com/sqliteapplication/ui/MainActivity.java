package ormliteapplication.sncf.com.sqliteapplication.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kotlinx.coroutines.Dispatchers;
import ormliteapplication.sncf.com.sqliteapplication.R;
import ormliteapplication.sncf.com.sqliteapplication.asynctask.LoadDataTask;
import ormliteapplication.sncf.com.sqliteapplication.asynctask.GetDataTask;
import ormliteapplication.sncf.com.sqliteapplication.asynctask.GetAllDataTask;
import ormliteapplication.sncf.com.sqliteapplication.asynctask.DeleteDataTask;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonInsert = findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDbWithParams();
            }
        });

        Button buttonTestQueryForAll = findViewById(R.id.buttonTestQueryForAll);
        buttonTestQueryForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAllDataTask(progressDialog, getApplication()).execute();
            }
        });

        Button buttonTestQueryWhere = findViewById(R.id.buttonTestQueryWhere);
        buttonTestQueryWhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recupererDonnesAvecParam();
            }
        });

        Button buttonSuppress = findViewById(R.id.buttonSuppress);
        buttonSuppress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteDataTask(progressDialog, getApplication()).execute();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading data");
    }

    private void recupererDonnesAvecParam() {
        EditText editText = findViewById(R.id.condition);
        Editable text = editText.getText();
        String condition = null;
        if (!TextUtils.isEmpty(text)) {
            condition = text.toString();
        }
        new GetDataTask(progressDialog, condition, getApplication()).execute();
    }

    private void initDbWithParams() {
        EditText editText = findViewById(R.id.nbEntrees);
        Editable text = editText.getText();
        int nbEntree = 100;
        if (!TextUtils.isEmpty(text)) {
            nbEntree = Integer.valueOf(text.toString());
        }
        new LoadDataTask(nbEntree, progressDialog, getApplication()).execute();
    }
}
