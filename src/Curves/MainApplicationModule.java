package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGComponent;
import Uniwork.UI.NGUIHelpToolboxContext;

public class MainApplicationModule extends NGVisualApplicationModule {

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGCustomStageItem item = FStageManager.addStageItem("Main", FPrimaryStage);
        item.setCaption(getDescription());
        item.setPosition(1000, 100);
    }

    @Override
    protected void registerObjectRequests() {
        super.registerObjectRequests();
        registerObjectRequest("Main", this, "Help", "ShowHelp");
    }

    public MainApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FDescription = "Main";
        FStageManager.registerItemClass("Main", "Curves.MainStageItem");
        FToolboxManager.registerItemClass("Help", "Uniwork.UI.NGUIHelpToolboxItem");
    }

    public void ShowHelp() {
        FToolboxManager.ShowToolbox("Help", String.format("%s.Help", getDescription()), new NGUIHelpToolboxContext(Application.LoadResourceFileContent("help/welcome.txt")));
    }

}
