package Curves;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public class CurveParameterDefinition extends NGObject {

    protected String FName;
    protected String FDescription;
    protected ArrayList<CurveParameterDefinitionArea> FDefintionArea;

    public CurveParameterDefinition(String aName, String aDescription) {
        super();
        FName = aName;
        FDescription = aDescription;
        FDefintionArea = new ArrayList<CurveParameterDefinitionArea>();
    }

    public String getName() {
        return FName;
    }

    public String getDescription() {
        return FDescription;
    }

    public void addDefinitionArea(double aMin, double aMax) {
        CurveParameterDefinitionArea area = new CurveParameterDefinitionArea(aMin, aMax);
        FDefintionArea.add(area);

    }

}
