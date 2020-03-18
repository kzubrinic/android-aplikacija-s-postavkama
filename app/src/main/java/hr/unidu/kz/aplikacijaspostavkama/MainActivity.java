package hr.unidu.kz.aplikacijaspostavkama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView slika;
    private EditText tekst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slika = findViewById(R.id.slika);
        tekst = findViewById(R.id.tekst);
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
                otvoriDrugu();
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

    private void otvoriDrugu(){
        Intent intent = new Intent(MainActivity.this,DrugaActivity.class);
        startActivity(intent);
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

    // Spremanje u spremište aktivnosti MainActivity
    public void spremiSpremisteAktivnosti(View view) {
        // 1. Pristupi inicijalnom spremištu postavki aktivnosti
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        // 2. Stvori objekt tipa Editor koji omogućuje uređivanje spremišta
        SharedPreferences.Editor editor = sp.edit();
        // 3. Zapiši u spremište cijeli broj s ključem "uneseni_broj"
        editor.putString("tekst", tekst.getText().toString());
        // 4. Potvrdi promjenu podataka u spremištu
        editor.commit();
    }

    // Spremanje u imenovano spremište aplikacije
    public void spremiImenovanoSpremiste(View view) {
        // 1. Pristupi inicijalnom spremištu postavki aktivnosti
        SharedPreferences sp = getSharedPreferences("moje_spremiste",  Context.MODE_PRIVATE);
        // 2. Stvori objekt tipa Editor koji omogućuje uređivanje spremišta
        SharedPreferences.Editor editor = sp.edit();
        // 3. Zapiši u spremište cijeli broj s ključem "uneseni_broj"
        editor.putString("tekst", tekst.getText().toString());
        // 4. Potvrdi promjenu podataka u spremištu
        editor.commit();
    }
}