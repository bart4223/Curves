package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGDisplayView;
import Uniwork.Visuals.NGGrid2DDisplayController;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CurveStageController extends NGStageController {

    @FXML
    private Canvas Layer0;

    @Override
    protected void CreateDisplayController() {
        super.CreateDisplayController();
        NGDisplayView dv = new NGDisplayView(Layer0.getWidth(), Layer0.getHeight());
        NGGrid2DDisplayController dcgrid = new NGGrid2DDisplayController(Layer0, "Grid");
        dcgrid.setView(dv);
        dcgrid.GridDistance = 20;
        dcgrid.GridColor = Color.DARKGRAY;
        registerDisplayController(dcgrid);
    }

    public CurveStageController() {
        this(null);
    }

    public CurveStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);

    }

}
