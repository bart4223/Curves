package Curves;

import Uniwork.Appl.NGApplication;

public class Main extends NGApplication {

    @Override
    public void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        MainApplicationModule main = (MainApplicationModule)FModuleManager.addModule(MainApplicationModule.class, "Main");
        main.setPrimaryStage(FPrimaryStage);
        FModuleManager.addModule(CurveApplicationModule.class, "One");
    }

    public Main() {
        super();
        FName = "Curves";
        FDescription = "Curves is a project to visualize and animate some mathematical functions";
        FConfigurationFilename = "resources/config.ccf";
    }

    public static void main(String[] args) {
        launch(args);
    }

}
