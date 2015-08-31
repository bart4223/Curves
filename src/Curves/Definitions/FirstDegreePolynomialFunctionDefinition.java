package Curves.Definitions;

import Curves.CurveParameterDefinition;
import Curves.CustomCurveDefinition;

public class FirstDegreePolynomialFunctionDefinition extends CustomCurveDefinition {

    public FirstDegreePolynomialFunctionDefinition() {
        super("y=ax+b", "First Degree Polynomial Function", "First degree polynomial function like ax+b");
        addParameter(CurveParameterDefinition.Kind.X, "x");
        addParameter(CurveParameterDefinition.Kind.Y, "y");
        addParameter(CurveParameterDefinition.Kind.Factor, "a");
        addParameter(CurveParameterDefinition.Kind.Factor, "b");
    }

}
