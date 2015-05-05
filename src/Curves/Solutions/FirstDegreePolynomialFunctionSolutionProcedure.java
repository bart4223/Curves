package Curves.Solutions;

import Curves.CurveParameterValueList;
import Curves.CustomCurveSolutionProcedure;

public class FirstDegreePolynomialFunctionSolutionProcedure extends CustomCurveSolutionProcedure {

    public FirstDegreePolynomialFunctionSolutionProcedure() {
        super();
        addSolveProblem("VALUE", "SolveProblemValue");
    }

    public void SolveProblemValue(CurveParameterValueList aValues) {
        double x = aValues.getValue("x");
        double a = aValues.getValue("a");
        double b = aValues.getValue("b");
        double y = a * x + b;
        aValues.setValue("y", y);
        writeInfo(String.format("x=%f y=%f", x, y));
    }

}
