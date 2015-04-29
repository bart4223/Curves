package Curves;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public class CurveParameterDefinition extends NGObject {

    public enum Kind {Factor, X, Y}

    protected String FName;
    protected String FDescription;
    protected ArrayList<CurveParameterDefinitionArea> FDefintionAreas;
    protected Kind FKind;

    public CurveParameterDefinition(Kind aKind, String aName) {
        this(aKind, aName, "");
    }

    public CurveParameterDefinition(Kind aKind, String aName, String aDescription) {
        super();
        FKind = aKind;
        FName = aName;
        FDescription = aDescription;
        FDefintionAreas = new ArrayList<CurveParameterDefinitionArea>();
    }

    public Kind getKind() {
        return FKind;
    }

    public String getName() {
        return FName;
    }

    public String getDescription() {
        return FDescription;
    }

    public void addDefinitionArea(double aMin, double aMax) {
        CurveParameterDefinitionArea area = new CurveParameterDefinitionArea(aMin, aMax);
        FDefintionAreas.add(area);

    }

}
