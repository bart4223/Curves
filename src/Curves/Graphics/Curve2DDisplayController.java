package Curves.Graphics;

import Curves.Curve2D;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Visuals.NGDisplayController;
import javafx.scene.canvas.Canvas;

public class Curve2DDisplayController extends NGDisplayController {

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.clearRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        if (Curve instanceof Curve2D) {
            for (NGPoint2D point: Curve.getPoints()) {
                drawPixel(point.getXAsInt() + (int) FCanvas.getWidth() / (2 * FPixelSize), (int) FCanvas.getHeight() / (2 * FPixelSize) - point.getYAsInt(), Curve.getLineColor());
            }
        }
    }

    public Curve2DDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public Curve2DDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        Curve = null;
        FPixelSize = 1;
    }

    public Curve2D Curve;

}
