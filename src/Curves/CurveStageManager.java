package Curves;

import Uniwork.Base.NGComponent;

import java.util.ArrayList;

public class CurveStageManager extends NGComponent {

    protected ArrayList<CurveStageItem> FItems;

    protected void DoInitialize() {
        super.DoInitialize();
        for (CurveStageItem item : FItems) {
            item.Initialize();
        }
    }

    protected void DoFinalize() {
        for (CurveStageItem item : FItems) {
            item.Finalize();
        }
        super.DoFinalize();
    }

    public CurveStageManager() {
        this(null);
    }

    public CurveStageManager(NGComponent aOwner) {
        super(aOwner);
        FItems = new ArrayList<CurveStageItem>();
    }

    public void newStage() {
        CurveStageItem item = new CurveStageItem(this);
        FItems.add(item);
    }

}
