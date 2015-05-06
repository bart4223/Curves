package Curves;

import Uniwork.Base.NGObject;

public class CurveProblemDefinition extends NGObject {

    protected String FName;
    protected String FDescription;
    protected String FSolveMethodName;

    public CurveProblemDefinition(String aName, String aSolveMethodName) {
        this(aName, aSolveMethodName, "");
    }

    public CurveProblemDefinition(String aName, String aSolveMethodName, String aDescription) {
        super();
        FName = aName;
        FSolveMethodName = aSolveMethodName;
        FDescription = aDescription;
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
