package Curves.Definition;

import Curves.CurveParameterDefinition;
import Curves.CustomCurveDefinition;

public class FirstDegreePolynomialFunctionDefinition extends CustomCurveDefinition {

    public FirstDegreePolynomialFunctionDefinition(double aMinA, double aMaxA, double aMinB, double aMaxB, double aMinX, double aMaxX) {
        super("First Degree Polynomial Function", "First degree polynomial function like ax+b");
        addParameter(CurveParameterDefinition.Kind.Y, "y");
        CurveParameterDefinition parDefX = addParameter(CurveParameterDefinition.Kind.X, "x");
        parDefX.addDefinitionArea(-aMinX, aMaxX);
        CurveParameterDefinition parDefA = addParameter(CurveParameterDefinition.Kind.Factor, "a");
        parDefA.addDefinitionArea(-aMinA, aMaxA);
        CurveParameterDefinition parDefB = addParameter(CurveParameterDefinition.Kind.Factor, "b");
        parDefB.addDefinitionArea(-aMinB, aMaxB);
    }

}
