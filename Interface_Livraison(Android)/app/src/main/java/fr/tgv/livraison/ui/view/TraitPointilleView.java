package fr.tgv.livraison.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import fr.tgv.livraison.R;

/**
 * Trait vertical pointillé reliant deux arrêts de la boucle (maquette : .stop-track > i,
 * « border-left: 1px dashed »). Un drawable XML « line » ne se dessine qu’horizontalement,
 * d’où cette vue. Utilisée dans item_arret.xml.
 */
public class TraitPointilleView extends View {

    private static final float EPAISSEUR_DP = 1f;
    private static final float TIRET_DP = 3f;
    private static final float ESPACE_DP = 3f;

    private final Paint pinceau = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path trace = new Path();

    public TraitPointilleView(Context context) {
        this(context, null);
    }

    public TraitPointilleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        float densite = getResources().getDisplayMetrics().density;
        pinceau.setColor(ContextCompat.getColor(context, R.color.arret_trait));
        pinceau.setStyle(Paint.Style.STROKE);
        pinceau.setStrokeWidth(EPAISSEUR_DP * densite);
        pinceau.setPathEffect(new DashPathEffect(
                new float[]{TIRET_DP * densite, ESPACE_DP * densite}, 0));
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        // drawPath (plutôt que drawLine) : DashPathEffect est bien rendu en accélération matérielle
        float x = getWidth() / 2f;
        trace.rewind();
        trace.moveTo(x, 0);
        trace.lineTo(x, getHeight());
        canvas.drawPath(trace, pinceau);
    }
}
