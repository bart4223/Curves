package Curves;

import Uniwork.Misc.NGLogEvent;

public interface CurveEventListener {

    void handleCurveAdded(CurveEvent e);
    void handleCurveCalculated(CurveEvent e);
    void handleLogAdded(NGLogEvent e);
    void handleLogClear();

}
