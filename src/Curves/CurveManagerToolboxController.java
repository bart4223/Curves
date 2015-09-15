package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObjectRequestItem;
import Uniwork.Visuals.NGToolboxController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CurveManagerToolboxController extends NGToolboxController {

    @FXML
    private ComboBox cbLineSizes;

    @FXML
    private ComboBox cbScale;

    @FXML
    protected void handlecbScale(ActionEvent actionEvent) {
        if (actionEvent.getEventType().equals(ActionEvent.ACTION) && cbLineSizes.getValue() != null) {
            NGObjectRequestItem aRequest = newObjectRequest("Curve", "Scale");
            double scale = Double.parseDouble(cbScale.getValue().toString());
            aRequest.addParam("aScale", scale);
            Invoke(aRequest);
        }
    }

    @FXML
    protected void handlecbLineSizes(ActionEvent actionEvent) {
        if (actionEvent.getEventType().equals(ActionEvent.ACTION) && cbLineSizes.getValue() != null) {
            NGObjectRequestItem aRequest = newObjectRequest("Curve", "LineSize");
            Integer lineSize = Integer.parseInt(cbLineSizes.getValue().toString());
            aRequest.addParam("aLineSize", lineSize);
            Invoke(aRequest);
        }
    }

    @FXML
    protected void handleGrid(ActionEvent actionEvent) {
        Invoke("CurveModule", "CurveGrid");
    }

    protected void DoInitialize() {
        super.DoInitialize();
        for (int i = 1; i <= 5; i++)
            cbLineSizes.getItems().add(i);
        cbLineSizes.getSelectionModel().select("1");
        for (Double i = 0.1; i <= 10; i = i * 10)
            cbScale.getItems().add(i);
        cbScale.getSelectionModel().select("1.0");
    }

    public CurveManagerToolboxController() {
        this(null);
    }

    public CurveManagerToolboxController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
