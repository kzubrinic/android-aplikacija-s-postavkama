package hr.unidu.kz.aplikacijaspostavkama;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView slika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slika = findViewById(R.id.slika);
        primijeniPostavke();
    }

    // Po pokretanju programa učitavaju se postavke ako postoje
    // Ako ih nema, primjenjuju se defaultne vrijendosti.
    private void primijeniPostavke() {
        // Dohvaća spremljene vrijednosti postavki
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean slikaSpavac = prefs.getBoolean("slika_spavac", true);
        String naslov = prefs.getString("naslov_aktivnosti", "Aplikacija s postavkama");
        String boja = prefs.getString("lista_boja", "#ffffff");
        // Postavlja sliku
        if (slikaSpavac)
            slika.setImageDrawable(getResources().getDrawable(R.drawable.smiley));
        else
            slika.setImageDrawable(getResources().getDrawable(R.drawable.smiley2));
        // Postavlja naslov aktivnosti
        setTitle(naslov);
        // Mijenja boju pozadine
        LinearLayout pozadina = findViewById(R.id.pozadina);
        pozadina.setBackgroundColor(Color.parseColor(boja));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Obrada akcija izbornika opcija
        switch (item.getItemId()) {
            case R.id.prva_stavka:
                prikaziPostavke();
                return true;
            case R.id.druga_stavka:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void prikaziPostavke() {
        Intent intent = new Intent(MainActivity.this, PrefsActivity.class);
        // Nakon što se izmjena postavki završi, želimo ažurirati
        // komponente aktivnosti pa koristimo ovu metodu (a ne "običnu" startActivity).
        startActivityForResult(intent, 7);
    }

    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        if (reqCode == 7) {
            // Aktivnost postavki je završila obradu, primjenjujemo postavke
            if (resCode == RESULT_OK) {
                primijeniPostavke();
            }
        }
    }
}