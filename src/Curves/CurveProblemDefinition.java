package Curves;

import Uniwork.Base.NGObject;

public class CurveProblemDefinition extends NGObject {

    public enum Kind {Value}

    protected String FName;
    protected String FDescription;
    protected String FSolveMethodName;
    protected Kind FKind;

    public CurveProblemDefinition(CurveProblemDefinition.Kind aKind, String aName, String aSolveMethodName) {
        this(aKind, aName, aSolveMethodName, "");
    }

    public CurveProblemDefinition(CurveProblemDefinition.Kind aKind, String aName, String aSolveMethodName, String aDescription) {
        super();
        FKind = aKind;
        FName = aName;
        FSolveMethodName = aSolveMethodName;
        FDescription = aDescription;
    }

    public CurveProblemDefinition.Kind getKind() {
        return FKind;
    }

    public String getName() {
        return FName;
    }

    public String getDescription() {
        return FDescription;
    }

    public String getSolveMethodName() {
        return FSolveMethodName;
    }

}
