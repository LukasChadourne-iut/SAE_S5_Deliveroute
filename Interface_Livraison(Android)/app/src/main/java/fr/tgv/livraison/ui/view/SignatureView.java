package fr.tgv.livraison.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Vue personnalisée permettant au client de signer le bon de livraison au doigt.
 * Utilisée dans fragment_signature_bon.xml.
 */
public class SignatureView extends View {

    private static final int COULEUR_TRAIT = 0xFF163326;
    private static final float EPAISSEUR_DP = 3f;

    private final Paint pinceau = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path trace = new Path();
    private boolean vide = true;

    public SignatureView(Context context) {
        this(context, null);
    }

    public SignatureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pinceau.setColor(COULEUR_TRAIT);
        pinceau.setStyle(Paint.Style.STROKE);
        pinceau.setStrokeCap(Paint.Cap.ROUND);
        pinceau.setStrokeJoin(Paint.Join.ROUND);
        pinceau.setStrokeWidth(EPAISSEUR_DP * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(trace, pinceau);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // Empêche le ScrollView parent de défiler pendant la signature
                getParent().requestDisallowInterceptTouchEvent(true);
                trace.moveTo(x, y);
                vide = false;
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getHistorySize(); i++) {
                    trace.lineTo(event.getHistoricalX(i), event.getHistoricalY(i));
                }
                trace.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    /** Efface la signature. */
    public void effacer() {
        trace.reset();
        vide = true;
        invalidate();
    }

    /** @return true si le client n’a encore rien signé. */
    public boolean estVide() {
        return vide;
    }

    /**
     * Exporte la signature (fond blanc) pour l’enregistrer dans un fichier local
     * puis l’envoyer à l’API avec le bon de livraison.
     *
     * @return le bitmap de la signature, ou null si la vue n’est pas encore mesurée
     */
    @Nullable
    public Bitmap versBitmap() {
        if (getWidth() == 0 || getHeight() == 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(trace, pinceau);
        return bitmap;
    }
}
