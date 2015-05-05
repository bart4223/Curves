package Curves;

import Uniwork.Graphics.NGPoint2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Curve2D extends CustomCurve {

    protected Color FLineColor;
    protected ArrayList<NGPoint2D> FPoints;

    @Override
    protected void DoAfterCalculate(String aProblemName) {
        if (aProblemName.equals("VALUE1")) {
            double x = FSolveParameterValues.getValue("x");
            double y = FSolveParameterValues.getValue("y");
            FPoints.add(new NGPoint2D(x, y));
        }
        super.DoAfterCalculate(aProblemName);
    }

    public Curve2D(CustomCurveDefinition aDefinition, CustomCurveSolutionProcedure aSolutionProcedure) {
        super(aDefinition, aSolutionProcedure);
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
