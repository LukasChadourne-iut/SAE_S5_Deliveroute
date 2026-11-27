package fr.tgv.livraison.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.tgv.livraison.R;
import fr.tgv.livraison.ui.fragment.BonsFragment;
import fr.tgv.livraison.ui.fragment.ColisFragment;
import fr.tgv.livraison.ui.fragment.PlusFragment;
import fr.tgv.livraison.ui.fragment.TourneeFragment;

/**
 * Activité principale : en-tête, conteneur de fragments et barre de navigation
 * (Tournée / Colis / Bons / Plus).
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvDate = findViewById(R.id.tvDate);
        String date = new SimpleDateFormat("EEEE d MMMM", Locale.FRANCE).format(new Date());
        tvDate.setText(date.substring(0, 1).toUpperCase(Locale.FRANCE) + date.substring(1));
        ((TextView) findViewById(R.id.tvTitreEntete)).setText(R.string.app_name);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment fragment;
            if (id == R.id.nav_colis) {
                fragment = new ColisFragment();
            } else if (id == R.id.nav_bons) {
                fragment = new BonsFragment();
            } else if (id == R.id.nav_plus) {
                fragment = new PlusFragment();
            } else {
                fragment = new TourneeFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        });

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_tournee);
        }
    }
}
