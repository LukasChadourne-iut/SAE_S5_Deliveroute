package fr.tgv.livraison.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import fr.tgv.livraison.R;

/**
 * Écran de connexion du livreur (annexe 2.3, précision 1).
 * Squelette : vérifie seulement que les champs sont remplis.
 * TODO : authentification auprès de l'API (Volley) et gestion des erreurs.
 */
public class ConnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        TextInputLayout tilIdentifiant = findViewById(R.id.tilIdentifiant);
        TextInputLayout tilMotDePasse = findViewById(R.id.tilMotDePasse);
        TextInputEditText etIdentifiant = findViewById(R.id.etIdentifiant);
        TextInputEditText etMotDePasse = findViewById(R.id.etMotDePasse);

        findViewById(R.id.btnConnexion).setOnClickListener(v -> {
            boolean identifiantVide = TextUtils.isEmpty(etIdentifiant.getText());
            boolean motDePasseVide = TextUtils.isEmpty(etMotDePasse.getText());
            tilIdentifiant.setError(identifiantVide ? getString(R.string.champ_obligatoire) : null);
            tilMotDePasse.setError(motDePasseVide ? getString(R.string.champ_obligatoire) : null);
            if (identifiantVide || motDePasseVide) {
                return;
            }
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
