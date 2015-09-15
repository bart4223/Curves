package Curves;

import Uniwork.Graphics.NGPoint2D;
import java.util.ArrayList;

public class Curve2D extends CustomCurve {

    protected ArrayList<NGPoint2D> FPoints;

    @Override
    protected void BeforeInternalCalculate(String aProblemName) {
        super.BeforeInternalCalculate(aProblemName);
        switch (FSolutionProcedure.getSolveProblem(aProblemName).getKind()) {
            case Value:
                FPoints.clear();
                writeInfo(CurvesConsts.C_DEBUGLEVEL_SOLVEPROBLEM, String.format("Value list cleared (Points %d)", FPoints.size()));
        }
    }

    @Override
    protected void DoAfterCalculate(String aProblemName) {
        switch (FSolutionProcedure.getSolveProblem(aProblemName).getKind()) {
            case Value:
                double x = FSolveParameterValues.getValue("x");
                double y = FSolveParameterValues.getValue("y");
                FPoints.add(new NGPoint2D(x, y));
                writeInfo(CurvesConsts.C_DEBUGLEVEL_SOLVEPROBLEM, String.format("New 2D-Point(%f/%f) added in value list (size %d)", x, y, FPoints.size()));
                break;
        }
        super.DoAfterCalculate(aProblemName);
    }

    @Override
    protected void AfterInternalCalculate(String aProblemName) {
        super.AfterInternalCalculate(aProblemName);
        switch (FSolutionProcedure.getSolveProblem(aProblemName).getKind()) {
            case Value:
                writeInfo(String.format("Value list with points count %d", FPoints.size()));
        }
    }

    public Curve2D(CurveManager aCurveManager, String aName, CustomCurveDefinition aDefinition) {
        this(aCurveManager, aName, aDefinition, null);
    }

    public Curve2D(CurveManager aCurveManager, String aName, CustomCurveDefinition aDefinition, CustomCurveSolutionProcedure aSolutionProcedure) {
        super(aCurveManager, aName, aDefinition, aSolutionProcedure);
        FPoints = new ArrayList<NGPoint2D>();
    }

    public ArrayList<NGPoint2D> getPoints() {
        ArrayList<NGPoint2D> res = new ArrayList<NGPoint2D>();
        for (NGPoint2D point : FPoints)
            res.add(new NGPoint2D(point.getX() / FScale, point.getY() / FScale));
        return res;
    }

    public void ClearPoints() {
        FPoints.clear();
    }

}
