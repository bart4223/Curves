package Curves;

import Uniwork.Base.NGObject;

public class CurveToolboxContext extends NGObject {

    public CustomCurve FCurve;

    public CurveToolboxContext(CustomCurve aCurve) {
        super();
        FCurve = aCurve;
    }

    public CustomCurve getCurve() {
        return FCurve;
    }

}
