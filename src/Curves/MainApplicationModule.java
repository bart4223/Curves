package Curves;

import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGComponent;

public class MainApplicationModule extends NGVisualApplicationModule {

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FStageManager.addStageItem("Main", FPrimaryStage);
    }

    public MainApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName, aDescription);
        FStageManager.registerItemClass("Main", "Curves.MainStageItem");
    }

}
