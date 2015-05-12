package Curves;

import Uniwork.Appl.NGApplication;

public class Main extends NGApplication {

    @Override
    public void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        FStageManager.registerItemClass("Main", "Curves.MainStageItem");
        FStageManager.registerItemClass("Curve", "Curves.CurveStageItem");
        FStageManager.addStageItem("Main", FPrimaryStage);
        FStageManager.addStageItem("Curve");
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
