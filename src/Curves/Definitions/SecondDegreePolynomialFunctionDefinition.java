package Curves.Definitions;

import Curves.CurveParameterDefinition;
import Curves.CustomCurveDefinition;
import Curves.Solutions.SecondDegreePolynomialFunctionSolutionProcedure;

public class SecondDegreePolynomialFunctionDefinition extends CustomCurveDefinition {

    public SecondDegreePolynomialFunctionDefinition() {
        super("y=ax^2+bx+c", "Second Degree Polynomial Function", "Second degree polynomial function like y=ax^2+bx+c");
        addParameter(CurveParameterDefinition.Kind.Y, "y");
        addParameter(CurveParameterDefinition.Kind.X, "x");
        addParameter(CurveParameterDefinition.Kind.Factor, "a");
        addParameter(CurveParameterDefinition.Kind.Factor, "b");
        addParameter(CurveParameterDefinition.Kind.Factor, "c");
    }

    public Class getDefaultSolutionProcedure() {
        return SecondDegreePolynomialFunctionSolutionProcedure.class;
    }

}
