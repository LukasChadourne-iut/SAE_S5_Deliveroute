package fr.tgv.livraison.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.tgv.livraison.R;
import fr.tgv.livraison.ui.ConnexionActivity;

/**
 * Écran « Plus » : profil, préférences, assistance, déconnexion.
 */
public class PlusFragment extends Fragment {

    public PlusFragment() {
        super(R.layout.fragment_plus);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.optDeconnexion).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ConnexionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
