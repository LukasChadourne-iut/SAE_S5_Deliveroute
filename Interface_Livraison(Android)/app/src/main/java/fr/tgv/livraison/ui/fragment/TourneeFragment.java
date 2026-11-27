package fr.tgv.livraison.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import fr.tgv.livraison.R;

/**
 * Écran « Tournée » : résumé, carte OpenStreetMap (osmdroid), itinéraire / chargement.
 * Squelette : affiche la carte centrée sur Strasbourg.
 * TODO : charger la tournée depuis l'API, remplir rvTournee, position en temps réel.
 */
public class TourneeFragment extends Fragment {

    private static final GeoPoint CENTRE_PAR_DEFAUT = new GeoPoint(48.5734, 7.7521);
    private static final double ZOOM_PAR_DEFAUT = 11.0;

    private MapView carte;

    public TourneeFragment() {
        super(R.layout.fragment_tournee);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Les serveurs de tuiles OpenStreetMap refusent les requêtes sans User-Agent
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());

        carte = view.findViewById(R.id.mapTournee);
        carte.setTileSource(TileSourceFactory.MAPNIK);
        carte.setMultiTouchControls(true);
        carte.getController().setZoom(ZOOM_PAR_DEFAUT);
        carte.getController().setCenter(CENTRE_PAR_DEFAUT);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (carte != null) {
            carte.onResume();
        }
    }

    @Override
    public void onPause() {
        if (carte != null) {
            carte.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        carte = null;
        super.onDestroyView();
    }
}
