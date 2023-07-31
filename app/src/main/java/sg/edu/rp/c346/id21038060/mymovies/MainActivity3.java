package sg.edu.rp.c346.id21038060.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    EditText etMovieId;
    EditText etTitle;
    EditText etGenre;
    EditText etYear;
    Spinner spnRatings;

    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etMovieId = findViewById(R.id.etMovieId);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spnRatings = findViewById(R.id.spnRatings);

        String[] yourDataArray = {"G", "PG", "PG13", "NC16", "M18", "R21"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list, yourDataArray);
        spnRatings.setAdapter(adapter);

        DBHelper db = new DBHelper(MainActivity3.this);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        etMovieId.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));

        switch (data.getRating()) {
            case "G":
                spnRatings.setSelection(0);
                break;
            case "PG":
                spnRatings.setSelection(1);
                break;
            case "PG13":
                spnRatings.setSelection(2);
                break;
            case "NC16":
                spnRatings.setSelection(3);
                break;
            case "M18":
                spnRatings.setSelection(4);
                break;
            default:  //R21
                spnRatings.setSelection(5);
                break;
        }

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setRating(spnRatings.getSelectedItem().toString());
                db.updateMovie(data);
                db.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                db.deleteMovie(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}