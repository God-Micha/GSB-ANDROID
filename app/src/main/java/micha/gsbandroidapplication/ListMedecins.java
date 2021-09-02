package micha.gsbandroidapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;


import micha.gsbandroidapplication.Modele.DAO;
import micha.gsbandroidapplication.Modele.Medecin;

public class ListMedecins extends AppCompatActivity {
    private List<String> listNoms;
    List<Medecin> listMed;
    private TextView myTextV;
    private ListView myList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on va chercher la vue
        setContentView(R.layout.activity_listmedecins);
        myTextV = findViewById(R.id.txVMed);
        myList = findViewById(R.id.list2);
        String text = (String) getIntent().getStringExtra("dep");
        myTextV.setText("Les médecins du " + text);
        new LisMed().execute();

    }
    private class LisMed extends AsyncTask {
        @Override protected Object doInBackground(Object[] params) {
            listNoms = DAO.getListInfo(DAO.getLesNoms(getIntent().getStringExtra("dep")));
            return null;
        }
        protected void onPostExecute(Object o) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListMedecins.this, android.R.layout.simple_list_item_1, listNoms);
            myList.setAdapter(adapter);
        }
    }

}
