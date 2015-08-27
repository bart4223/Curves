package Curves;

public interface CurveEventListener {

    void handleCurveAdded(CurveEvent e);
    void handleCurveCalculated(CurveEvent e);
    void handleCurrentCurveChanged(CurveEvent e);

}
