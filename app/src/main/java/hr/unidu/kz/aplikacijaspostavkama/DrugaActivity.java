package hr.unidu.kz.aplikacijaspostavkama;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DrugaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druga);
        TextView tekst = findViewById(R.id.tekst);
        // 1. Pristupi imenovanom spremištu postavki aplikacije
        SharedPreferences sp = getSharedPreferences("moje_spremiste",  Context.MODE_PRIVATE);
        // 2. Dohvati podatak iz spremišta
        String t = sp.getString("tekst","Tekst nije spremljen u spremište!");
        tekst.setText(t);
    }
}
