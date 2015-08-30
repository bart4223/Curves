package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGToolboxController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class CurveToolboxController extends NGToolboxController {

    protected class SliderChangeListener implements ChangeListener {

        protected Slider FSlider;

        public SliderChangeListener(Slider aSlider) {
            super();
            FSlider = aSlider;
        }

        @Override
        public void changed(ObservableValue observableValue, Object o, Object t1) {
            FCurve.setParameterValue(FSlider.getId(), (Double)t1);
            FCurve.getCurveManager().CalculateCurve(FCurve);
        }

    }

    @FXML
    private VBox VBox;

    @FXML
    private TextField edName;

    @FXML
    private Text lDesc;

    @FXML
    private Text lFormula;

    protected ArrayList<Control> FControls;

    protected CustomCurve FCurve;

    protected void CompositeStage() {
        edName.setText(FCurve.getName());
        lDesc.setText(FCurve.getDescription());
        lFormula.setText(FCurve.getFormula());
        lFormula.setStroke(FCurve.getLineColor());
        while (FControls.size() > 0) {
            VBox.getChildren().remove(FControls.get(0));
            FControls.remove(0);
        }
        Iterator<CurveParameterDefinition> itr = FCurve.getDefinition().getParameters();
        while (itr.hasNext()) {
            CurveParameterDefinition def = itr.next();
            switch (def.getKind()) {
                case Factor:
                    Slider slider = new Slider();
                    slider.setId(def.getName());
                    Iterator<CurveParameterDefinitionArea> itrArea = def.getDefinitionAreas();
                    while (itrArea.hasNext()) {
                        CurveParameterDefinitionArea area = itrArea.next();
                        slider.setMin(area.getMin());
                        slider.setMax(area.getMax());
                    }
                    slider.setTooltip(new Tooltip(def.getName()));
                    slider.setValue(FCurve.getParameterValue(def.getName()));
                    slider.valueProperty().addListener(new SliderChangeListener(slider));
                    FControls.add(slider);
                    VBox.getChildren().add(slider);
                    break;
            }
        }
    }

    public CurveToolboxController() {
        this(null);
    }

    public CurveToolboxController(NGCustomStageItem aStageItem) {
        super(aStageItem);
        FControls = new ArrayList<Control>();
    }

    public void setContext(CustomCurve aCurve) {
        FCurve = aCurve;
        CompositeStage();
    }

    public void UpdateStage(CustomCurve aCurve) {
        if (FCurve.equals(aCurve)) {
            lFormula.setText(FCurve.getFormula());
        }
    }

}
