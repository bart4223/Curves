package Curves;

import Uniwork.Base.NGObject;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class CustomCurveDefinition extends NGObject {

    protected String FName;
    protected String FDescription;
    protected String FFormula;
    protected ArrayList<CurveParameterDefinition> FParameters;

    protected CurveParameterDefinition addParameter(CurveParameterDefinition.Kind aKind, String aName) {
        return addParameter(aKind, aName, "");
    }

    protected CurveParameterDefinition addParameter(CurveParameterDefinition.Kind aKind, String aName, String aDescription) {
        CurveParameterDefinition res = new CurveParameterDefinition(aKind, aName, aDescription);
        FParameters.add(res);
        return res;
    }

    public CustomCurveDefinition(String aFormula) {
        this(aFormula, "");
    }

    public CustomCurveDefinition(String aFormula, String aName) {
        this(aFormula, aName, "");
    }

    public CustomCurveDefinition(String aFormula, String aName, String aDescription) {
        super();
        FResolver = null;
        FName = aName;
        FDescription = aDescription;
        FFormula = aFormula;
        FParameters = new ArrayList<CurveParameterDefinition>();
    }

    public String getName() {
        return FName;
    }

    public String getDescription() {
        return FDescription;
    }

    public String getFormula() {
        return FFormula;
    }

    public CurveParameterDefinition getParameterDefintion(String aName) {
        for (CurveParameterDefinition parameter : FParameters) {
            if (parameter.getName().equals(aName)) {
                return parameter;
            }
        }
        return null;
    }

    public CurveParameterDefinition getParameterDefintion(CurveParameterDefinition.Kind aKind) {
        for (CurveParameterDefinition parameter : FParameters) {
            if (parameter.getKind() == aKind) {
                return parameter;
            }
        }
        return null;
    }

    public Iterator<CurveParameterDefinition> getParameters() {
        return FParameters.iterator();
    }

    public CurveParameterDefinitionArea addDefinitionArea(String aName, Double aMin, Double aMax) {
        CurveParameterDefinition def = getParameterDefintion(aName);
        return def.addDefinitionArea(aMin, aMax);
    }

    public Class getDefaultSolutionProcedure() {
        return null;
    }

}
