package micha.gsbandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import micha.gsbandroidapplication.Modele.DAO;

public class MainActivity extends AppCompatActivity {
    private List<String> listDep;
    private ListView myList;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on va chercher la vue
        setContentView(R.layout.activity_main);
        new LisDep().execute();
        myList = findViewById(R.id.listMedecins);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewMedecin = new Intent(getApplicationContext(), ListMedecins.class);
                viewMedecin.putExtra("dep", (String) parent.getItemAtPosition(position));
                startActivity(viewMedecin);
            }

        });

    }

    private class LisDep extends AsyncTask {
        @Override protected Object doInBackground(Object[] params) {
            listDep = DAO.getLesDep();
            return null;
        }
        protected void onPostExecute(Object o) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listDep);
            myList.setAdapter(adapter);
        }
    }
}