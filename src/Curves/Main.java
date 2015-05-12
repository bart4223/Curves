package Curves;

import Uniwork.Appl.NGApplication;

public class Main extends NGApplication {

    @Override
    public void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        FStageManager.registerItemClass("MAIN", "Curves.MainStageItem");
        FStageManager.registerItemClass("CURVE", "Curves.CurveStageItem");
        FStageManager.addStageItem("MAIN", FPrimaryStage);
        FStageManager.addStageItem("CURVE");
    }

    public Main() {
        super();
        FName = "Curves";
        FConfigurationFilename = "resources/config.ccf";
    }

    public static void main(String[] args) {
        launch(args);
    }

}
