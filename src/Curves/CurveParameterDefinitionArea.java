package Curves;

import Uniwork.Base.NGObject;

public class CurveParameterDefinitionArea extends NGObject {

    public double FMin;
    public double FMax;

    public CurveParameterDefinitionArea(double aMin, double aMax) {
        super();
        FMin = aMin;
        FMax = aMax;
    }

    public double getMin() {
        return FMin;
    }

    public double getMax() {
        return FMax;
    }

}
