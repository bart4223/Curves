package Curves;

import Uniwork.Appl.NGApplication;

public class Main extends NGApplication {

    @Override
    public void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        MainApplicationModule main = (MainApplicationModule)FModuleManager.addModule(MainApplicationModule.class, "Main");
        main.setPrimaryStage(FPrimaryStage);
    }

    @Override
    protected void registerObjectRequests() {
        super.registerObjectRequests();
        registerObjectRequest("Application", this, "addCurveModule", "addCurveModule");
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

    public void addCurveModule() {
        addModule(CurveApplicationModule.class, true, String.format("%d", FModuleManager.getModuleCount()));
    }

}
