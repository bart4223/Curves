package Curves;

import Curves.Definitions.FirstDegreePolynomialFunctionDefinition;
import Curves.Definitions.SecondDegreePolynomialFunctionDefinition;
import Curves.Definitions.ThirdDegreePolynomialFunctionDefinition;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGRandomGenerator;
import Uniwork.Visuals.NGToolboxController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class AddCurveToolboxController extends NGToolboxController {

    protected Integer FStageSize = 800;
    protected CopyOnWriteArrayList<Control> FControls;

    protected class SliderChangeListener implements ChangeListener {

        protected Slider FSlider;
        protected Label FLabel;

        public SliderChangeListener(Slider aSlider, Label aLabel) {
            super();
            FSlider = aSlider;
            FLabel = aLabel;
        }

        @Override
        public void changed(ObservableValue observableValue, Object o, Object t1) {
            FLabel.setText(String.format("%s: %.2f", FSlider.getId(), (Double)t1));
        }

    }

    @FXML
    private TextField edName;

    @FXML
    private ComboBox cbCurveDefs;

    @FXML
    private VBox VBox;

    @FXML
    private Text lFormula;

    @FXML
    protected void handlecbCurveDefs(ActionEvent actionEvent) {
        CompositeStage();
    }

    @FXML
    protected void handleAdd(ActionEvent actionEvent) {
        if (cbCurveDefs.getSelectionModel().getSelectedItem() != null) {
            String classname = cbCurveDefs.getSelectionModel().getSelectedItem().toString();
            try {
                CustomCurveDefinition def = (CustomCurveDefinition)CurveManager.getClass().getClassLoader().loadClass(classname).getConstructor().newInstance();
                CustomCurve curve = new Curve2D(CurveManager, edName.getText(), def);
                Iterator<CurveParameterDefinition> itr = def.getParameters();
                while (itr.hasNext()) {
                    CurveParameterDefinition paramdef = itr.next();
                    switch (paramdef.getKind()) {
                        case Factor:
                            Double value = getParameterValue(paramdef.getName());
                            paramdef.addDefinitionArea(-value, value);
                            curve.setParameterValue(paramdef.getName(), NGRandomGenerator.GlobalRandomGenerator.getDouble() * value);
                            break;
                        case X:
                            paramdef.addDefinitionArea(-FStageSize / 2, FStageSize / 2);
                            break;
                    }
                }
                Color lineColor = new Color(0.0, NGRandomGenerator.GlobalRandomGenerator.getDouble(), NGRandomGenerator.GlobalRandomGenerator.getDouble(), NGRandomGenerator.GlobalRandomGenerator.getDouble());
                curve.setLineColor(lineColor);
                CurveManager.addCurve(curve);
            }
            catch (Exception e) {
                CurveManager.getLogManager().writeLog(0, e.getMessage(), NGLogEntry.LogType.Error);
            }
            FStageItem.Close();
        }
    }

    protected double getParameterValue(String aName) {
        Double res = 0.0;
        for (Control control: FControls) {
            if (control instanceof Slider) {
                if (control.getId().equals(aName))
                    res = ((Slider)control).getValue();
            }
        }
        return res;
    }

    protected void CompositeStage() {
        while (FControls.size() > 0) {
            VBox.getChildren().remove(FControls.get(0));
            FControls.remove(0);
        }
        if (cbCurveDefs.getSelectionModel().getSelectedItem() != null) {
            String classname = cbCurveDefs.getSelectionModel().getSelectedItem().toString();
            try {
                CustomCurveDefinition def = (CustomCurveDefinition)CurveManager.getClass().getClassLoader().loadClass(classname).getConstructor().newInstance();
                lFormula.setText(def.getFormula());
                Iterator<CurveParameterDefinition> itr = def.getParameters();
                while (itr.hasNext()) {
                    CurveParameterDefinition paramdef = itr.next();
                    switch (paramdef.getKind()) {
                        case Factor:
                            Label label = new Label(String.format("%s: 0,0", paramdef.getName()));
                            Slider slider = new Slider();
                            slider.setId(paramdef.getName());
                            slider.setTooltip(new Tooltip(paramdef.getName()));
                            slider.setMin(0.0);
                            slider.setMax(FStageSize / 2);
                            slider.valueProperty().addListener(new SliderChangeListener(slider, label));
                            FControls.add(label);
                            FControls.add(slider);
                            VBox.getChildren().add(VBox.getChildren().size() - 1, label);
                            VBox.getChildren().add(VBox.getChildren().size() - 1, slider);
                            break;
                    }
                }
            }
            catch (Exception e) {
                CurveManager.getLogManager().writeLog(0, e.getMessage(), NGLogEntry.LogType.Error);
            }
        }
    }

    protected void DoInitialize() {
        super.DoInitialize();
        cbCurveDefs.getItems().add(FirstDegreePolynomialFunctionDefinition.class.getName());
        cbCurveDefs.getItems().add(SecondDegreePolynomialFunctionDefinition.class.getName());
        cbCurveDefs.getItems().add(ThirdDegreePolynomialFunctionDefinition.class.getName());
    }

    public AddCurveToolboxController() {
        this(null);
    }

    public AddCurveToolboxController(NGCustomStageItem aStageItem) {
        super(aStageItem);
        FControls = new CopyOnWriteArrayList<Control>();
    }

    public CurveManager CurveManager;

}
