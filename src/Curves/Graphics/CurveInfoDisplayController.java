package Curves.Graphics;

import Uniwork.Graphics.NGPoint2D;
import Uniwork.Visuals.NGDisplayController;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CurveInfoDisplayController extends NGDisplayController {

    protected NGPoint2D FMousePos;
    protected Font FTextFont;

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.clearRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        FGC.setStroke(Color.BLACK);
        FGC.setFont(FTextFont);
        FGC.strokeText(String.format("Position %.0f/%.0f", FMousePos.getX(), FMousePos.getY()), 670, 770);
    }

    public CurveInfoDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public CurveInfoDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        FMousePos = new NGPoint2D(0.0, 0.0);
        FTextFont = new Font("Arial", 12);
    }

    public void setMousePos(Double aX, Double aY) {
        FMousePos.setX(aX - FCanvas.getWidth() / 2);
        FMousePos.setY(FCanvas.getHeight() / 2 - aY);
    }

}
