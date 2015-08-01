package Curves;

import Curves.Graphics.Curve2DDisplayController;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CurveStageController extends NGStageController {

    @FXML
    private AnchorPane AnchorPane0;

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
        dcgrid.AlternateGridColor = false;
        registerDisplayController(dcgrid);
    }

    public CurveStageController() {
        this(null);
    }

    public CurveStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

    public void addCurve(CustomCurve aCurve) {
        Canvas canvas = new Canvas();
        canvas.setHeight(AnchorPane0.getHeight());
        canvas.setWidth(AnchorPane0.getWidth());
        AnchorPane0.getChildren().add(canvas);
        if (aCurve instanceof Curve2D) {
            Curve2DDisplayController dccurve = new Curve2DDisplayController(canvas, String.format("Curve.%s",aCurve.getName()));
            dccurve.Curve = (Curve2D)aCurve;
            registerDisplayController(dccurve, true);
        }
    }

    public void RenderCurve(CustomCurve aCurve) {
        for (DisplayControllerItem dcitem : FDCItems) {
            NGDisplayController dc = dcitem.getDisplayController();
            if (dc instanceof Curve2DDisplayController && ((Curve2DDisplayController)dc).Curve.equals(aCurve)) {
                RenderScene(dcitem);
            }
        }
    }

}
