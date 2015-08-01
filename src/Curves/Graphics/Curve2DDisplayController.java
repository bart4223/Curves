package Curves.Graphics;

import Curves.Curve2D;
import Uniwork.Visuals.NGDisplayController;
import javafx.scene.canvas.Canvas;

public class Curve2DDisplayController extends NGDisplayController{

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.clearRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        if (Curve instanceof Curve2D) {
            FGC.setFill(Curve.getLineColor());
            FGC.fillRect(0, 0, 100, 100);
        }
    }

    public Curve2DDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public Curve2DDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        Curve = null;
    }

    public Curve2D Curve;

}
