package Curves;

import Curves.Graphics.CartesianCoordinateSystem2DDisplayController;
import Curves.Graphics.Curve2DDisplayController;
import Curves.Graphics.CurveInfoDisplayController;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CurveStageController extends NGStageController {

    @FXML
    private AnchorPane AnchorPane0;

    @FXML
    private Canvas Layer0;

    @FXML
    private Canvas Layer1;

    @FXML
    private Canvas LayerTop;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        LayerTop.addEventHandler(MouseEvent.MOUSE_MOVED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        HandleMouseMoved(t);
                    }
                });
    }

    @Override
    protected void CreateDisplayController() {
        super.CreateDisplayController();
        NGDisplayView dv = new NGDisplayView(Layer0.getWidth(), Layer0.getHeight());
        NGGrid2DDisplayController dcgrid = new NGGrid2DDisplayController(Layer0, "Grid");
        dcgrid.setView(dv);
        dcgrid.GridDistance = 10;
        dcgrid.GridColor = Color.DARKGRAY;
        dcgrid.AlternateGridColor = getConfigurationPropertyAsBoolean("CurveApplicationModule.AlternateGridColor", false);
        registerDisplayController(dcgrid);
        CartesianCoordinateSystem2DDisplayController dcCCS = new CartesianCoordinateSystem2DDisplayController(Layer1, "CCS");
        dcCCS.setView(dv);
        dcCCS.Distance = dcgrid.GridDistance;
        registerDisplayController(dcCCS);
        CurveInfoDisplayController dcCI = new CurveInfoDisplayController(LayerTop, "CI");
        dcCI.setView(dv);
        registerDisplayController(dcCI);
    }

    protected void HandleMouseMoved(MouseEvent t) {
        CurveInfoDisplayController dcCI = (CurveInfoDisplayController)getDisplayController("CI");
        dcCI.setMousePos(t.getX(), t.getY());
        RenderScene(dcCI);
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
        AnchorPane0.getChildren().add(AnchorPane0.getChildren().size() - 2, canvas);
        if (aCurve instanceof Curve2D) {
            Curve2DDisplayController dccurve = new Curve2DDisplayController(canvas, String.format("Curve.%s",aCurve.getID()));
            dccurve.Curve = (Curve2D)aCurve;
            registerDisplayController(dccurve, true);
        }
    }

    public void RenderCurve(CustomCurve aCurve) {
        for (DisplayControllerItem dcitem : FDCItems) {
            NGDisplayController dc = dcitem.getDisplayController();
            if (dc instanceof Curve2DDisplayController && ((Curve2DDisplayController)dc).Curve.equals(aCurve)) {
                RenderScene(dcitem);
                break;
            }
        }
    }

    public void RemoveCurve(CustomCurve aCurve) {
        for (DisplayControllerItem dcitem : FDCItems) {
            NGDisplayController dc = dcitem.getDisplayController();
            if (dc instanceof Curve2DDisplayController && ((Curve2DDisplayController)dc).Curve.equals(aCurve)) {
                for (Node node : AnchorPane0.getChildren()) {
                    if (node.equals(dc.getCanvas())) {
                        AnchorPane0.getChildren().remove(node);
                        break;
                    }
                }
                unregisterDisplayController(dc);
                break;
            }
        }
    }

    public void ToggleGrid() {
        NGGrid2DDisplayController dcgrid = (NGGrid2DDisplayController)getDisplayController("Grid");
        dcgrid.DrawGrid = !dcgrid.DrawGrid;
        RenderScene(dcgrid);
    }

    public void ScaleChanged(CurveManager aCurveManager) {
        CurveInfoDisplayController dcCI = (CurveInfoDisplayController)getDisplayController("CI");
        dcCI.Scale = aCurveManager.getScale();
        RenderScene(dcCI);
    }

}
