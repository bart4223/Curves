package Curves;

import Uniwork.Base.NGObject;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CurveParameterDefinition extends NGObject {

    public enum Kind {Factor, X, Y}

    protected String FName;
    protected String FDescription;
    protected CopyOnWriteArrayList<CurveParameterDefinitionArea> FDefintionAreas;
    protected Kind FKind;

    public CurveParameterDefinition(Kind aKind, String aName) {
        this(aKind, aName, "");
    }

    public CurveParameterDefinition(Kind aKind, String aName, String aDescription) {
        super();
        FKind = aKind;
        FName = aName;
        FDescription = aDescription;
        FDefintionAreas = new CopyOnWriteArrayList<CurveParameterDefinitionArea>();
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

    public CurveParameterDefinitionArea addDefinitionArea(double aMin, double aMax) {
        CurveParameterDefinitionArea area = new CurveParameterDefinitionArea(aMin, aMax);
        FDefintionAreas.add(area);
        return area;
    }

    public Iterator<CurveParameterDefinitionArea> getDefinitionAreas() {
        return FDefintionAreas.iterator();
    }

    public CurveParameterDefinitionArea getMin() {
        Double min = 0.0;
        CurveParameterDefinitionArea res = null;
        for (CurveParameterDefinitionArea area : FDefintionAreas) {
            if (area.getMin() < min) {
                min = area.getMin();
                res = area;
            }
        }
        return res;
    }

    public CurveParameterDefinitionArea getMax() {
        Double max = 0.0;
        CurveParameterDefinitionArea res = null;
        for (CurveParameterDefinitionArea area : FDefintionAreas) {
            if (area.getMax() > max) {
                max = area.getMax();
                res = area;
            }
        }
        return res;
    }

}
