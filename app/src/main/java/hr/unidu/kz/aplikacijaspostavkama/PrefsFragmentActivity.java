package hr.unidu.kz.aplikacijaspostavkama;

import android.os.Bundle;
import android.preference.PreferenceFragment;

// Klasa za ažuriranje postavaki - spremanje se odrađuje automatski
//  po promjeni određene postavke
public class PrefsFragmentActivity extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_stavke);
    }
}