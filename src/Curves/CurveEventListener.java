package Curves;

import java.util.EventObject;

public interface CurveEventListener {

    void handleCurveAdded(CurveEvent e);
    void handleCurveChanged(CurveEvent e);
    void handleCurveRemoved(CurveEvent e);
    void handleCurveCalculated(CurveEvent e);
    void handleCurrentCurveChanged(CurveEvent e);
    void handleScaleChanged(EventObject e);

}
