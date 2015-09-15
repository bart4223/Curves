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
        if (Curve != null) {
            FGC.setStroke(Curve.getLineColor());
            FGC.setLineWidth(Curve.getLineSize());
            FGC.beginPath();
            int index = 0;
            for (NGPoint2D point: Curve.getPointsWithScale()) {
                if (index == 0)
                    FGC.moveTo(point.getXAsInt() + (int) FCanvas.getWidth() / 2, (int) FCanvas.getHeight() / 2 - point.getYAsInt());
                else {
                    FGC.lineTo(point.getXAsInt() + (int) FCanvas.getWidth() / 2, (int) FCanvas.getHeight() / 2 - point.getYAsInt());
                    FGC.moveTo(point.getXAsInt() + (int) FCanvas.getWidth() / 2, (int) FCanvas.getHeight() / 2 - point.getYAsInt());
                }
                index++;
            }
            FGC.stroke();
            FGC.closePath();
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
