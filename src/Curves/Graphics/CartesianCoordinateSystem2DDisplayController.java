package Curves.Graphics;

import Uniwork.Visuals.NGDisplayController;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CartesianCoordinateSystem2DDisplayController extends NGDisplayController {

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.clearRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        Integer index = 0;
        Integer length;
        FGC.setStroke(Color.BLACK);
        FGC.setLineWidth(FPixelSize);
        FGC.beginPath();
        FGC.moveTo((int) FCanvas.getWidth() / 2, 0);
        FGC.lineTo((int) FCanvas.getWidth() / 2, FCanvas.getHeight());
        for (double y = 0.0; y <= FCanvas.getHeight(); y = y + Distance) {
            FGC.moveTo((int) FCanvas.getWidth() / 2, y);
            if (index % 2 == 0)
                length = StrokeLength;
            else
                length = StrokeLength / 2;

            FGC.lineTo((int) FCanvas.getWidth() / 2 + length, y);
            index = index + 1;
        }
        FGC.moveTo(0, (int) FCanvas.getHeight() / 2);
        FGC.lineTo(FCanvas.getWidth(), (int) FCanvas.getHeight() / 2);
        for (double x = 0.0; x <= FCanvas.getWidth(); x = x + Distance) {
            FGC.moveTo(x, (int) FCanvas.getHeight() / 2);
            if (index % 2 == 0)
                length = StrokeLength;
            else
                length = StrokeLength / 2;

            FGC.lineTo(x, (int) FCanvas.getHeight() / 2 - length);
            index = index + 1;
        }
        FGC.stroke();
        FGC.closePath();
    }

    public CartesianCoordinateSystem2DDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public CartesianCoordinateSystem2DDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        Distance = 10;
        StrokeLength = 10;
        FPixelSize = 1;
    }

    public int Distance;
    public int StrokeLength;

}
