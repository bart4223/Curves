package Curves.Solutions;

import Curves.CurveParameterValueList;
import Curves.CurveProblemDefinition;
import Curves.CurvesConsts;
import Curves.CustomCurveSolutionProcedure;

public class FirstDegreePolynomialFunctionSolutionProcedure extends CustomCurveSolutionProcedure {

    public FirstDegreePolynomialFunctionSolutionProcedure() {
        super();
        addSolveProblem(CurveProblemDefinition.Kind.Value, "Value", "SolveProblemValue");
    }

    public void SolveProblemValue(CurveParameterValueList aValues) {
        double x = aValues.getValue("x");
        double a = aValues.getValue("a");
        double b = aValues.getValue("b");
        double y = a * x + b;
        aValues.setValue("y", y);
        writeInfo(CurvesConsts.C_DEBUGLEVEL_SOLVEPROBLEM, String.format("x=%f y=%f", x, y));
    }

}
