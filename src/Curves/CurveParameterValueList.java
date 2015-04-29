package Curves;

import Uniwork.Base.NGPropertyList;

public class CurveParameterValueList extends NGPropertyList {

    public CurveParameterValueList() {
        super();
    }

    public double getValue(String aName) {
        return (Double)get(aName);
    }

    public void setValue(String aName, double aValue) {
        set(aName, aValue);
    }
}
