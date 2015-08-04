package Curves.Definitions;

import Curves.CurveParameterDefinition;
import Curves.CustomCurveDefinition;

public class SecondDegreePolynomialFunctionDefinition extends CustomCurveDefinition {

    public SecondDegreePolynomialFunctionDefinition(double aMinA, double aMaxA, double aMinB, double aMaxB, double aMinC, double aMaxC, double aMinX, double aMaxX) {
        super("y=ax²+bx+c", "Second Degree Polynomial Function", "Second degree polynomial function like y=ax²+bx+c");
        addParameter(CurveParameterDefinition.Kind.Y, "y");
        CurveParameterDefinition parDefX = addParameter(CurveParameterDefinition.Kind.X, "x");
        parDefX.addDefinitionArea(aMinX, aMaxX);
        CurveParameterDefinition parDefA = addParameter(CurveParameterDefinition.Kind.Factor, "a");
        parDefA.addDefinitionArea(aMinA, aMaxA);
        CurveParameterDefinition parDefB = addParameter(CurveParameterDefinition.Kind.Factor, "b");
        parDefB.addDefinitionArea(aMinB, aMaxB);
        CurveParameterDefinition parDefC = addParameter(CurveParameterDefinition.Kind.Factor, "c");
        parDefC.addDefinitionArea(aMinC, aMaxC);
    }

}
