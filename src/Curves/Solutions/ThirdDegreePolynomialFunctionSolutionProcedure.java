package Curves.Solutions;

import Curves.CurveParameterValueList;
import Curves.CurveProblemDefinition;
import Curves.CurvesConsts;
import Curves.CustomCurveSolutionProcedure;

public class ThirdDegreePolynomialFunctionSolutionProcedure extends CustomCurveSolutionProcedure {

    public ThirdDegreePolynomialFunctionSolutionProcedure() {
        super();
        addSolveProblem(CurveProblemDefinition.Kind.Value, "Value", "SolveProblemValue");
    }

    public void SolveProblemValue(CurveParameterValueList aValues) {
        double x = aValues.getValue("x");
        double a = aValues.getValue("a");
        double b = aValues.getValue("b");
        double c = aValues.getValue("c");
        double d = aValues.getValue("d");
        double y = a * x * x * x + b * x * x + c * x + d;
        aValues.setValue("y", y);
        writeInfo(CurvesConsts.C_DEBUGLEVEL_SOLVEPROBLEM, String.format("x=%f y=%f", x, y));
    }

}
