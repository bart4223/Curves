package Curves;

import Uniwork.Graphics.NGPoint2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Curve2D extends CustomCurve {

    protected Color FLineColor;
    protected ArrayList<NGPoint2D> FPoints;

    @Override
    protected void DoAfterCalculate(String aProblemName) {
        if (aProblemName.equals("VALUE")) {
            double x = FSolveParameterValues.getValue("x");
            double y = FSolveParameterValues.getValue("y");
            FPoints.add(new NGPoint2D(x, y));
            writeInfo(String.format("New 2D-Point(%f/%f) added in value list (size %d)", x, y, FPoints.size()));
        }
        super.DoAfterCalculate(aProblemName);
    }

    public Curve2D(CurveManager aCurveManager, CustomCurveDefinition aDefinition, CustomCurveSolutionProcedure aSolutionProcedure) {
        super(aCurveManager, aDefinition, aSolutionProcedure);
        FLineColor = Color.BLUE;
        FPoints = new ArrayList<NGPoint2D>();
    }

    public void setLineColor(Color aColor) {
        FLineColor = aColor;
    }

    public Color getLineColor() {
        return FLineColor;
    }

    public ArrayList<NGPoint2D> getPoints() {
        return FPoints;
    }

    public void ClearPoints() {
        FPoints.clear();
    }

}
