package Curves.Definitions;

import Curves.CurveParameterDefinition;
import Curves.CustomCurveDefinition;
import Curves.Solutions.ThirdDegreePolynomialFunctionSolutionProcedure;

public class ThirdDegreePolynomialFunctionDefinition extends CustomCurveDefinition {

    public ThirdDegreePolynomialFunctionDefinition() {
        super("y=ax^3+bx^2+cx+d", "Third Degree Polynomial Function", "Third degree polynomial function like y=ax^3+bx^2+cx+d");
        addParameter(CurveParameterDefinition.Kind.Y, "y");
        addParameter(CurveParameterDefinition.Kind.X, "x");
        addParameter(CurveParameterDefinition.Kind.Factor, "a");
        addParameter(CurveParameterDefinition.Kind.Factor, "b");
        addParameter(CurveParameterDefinition.Kind.Factor, "c");
        addParameter(CurveParameterDefinition.Kind.Factor, "d");
    }

    public Class getDefaultSolutionProcedure() {
        return ThirdDegreePolynomialFunctionSolutionProcedure.class;
    }

}
