package Curves;

import java.util.EventObject;

public class CurveEvent extends EventObject {

    protected CustomCurve FCurve;

    public CurveEvent(Object source, CustomCurve aCurve) {
        super(source);
        FCurve = aCurve;
    }

    public CustomCurve getCurve() {
        return FCurve;
    }

}
