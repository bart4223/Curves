package Curves;

public interface CurveEventListener {

    void handleCurveAdded(CurveEvent e);
    void handleCurveChanged(CurveEvent e);
    void handleCurveCalculated(CurveEvent e);
    void handleCurrentCurveChanged(CurveEvent e);

}
