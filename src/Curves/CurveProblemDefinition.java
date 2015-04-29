package Curves;

import Uniwork.Base.NGObject;

public class CurveProblemDefinition extends NGObject {

    protected String FName;
    protected String FDescription;
    protected String FMethodName;

    public CurveProblemDefinition(String aName, String aMethodName) {
        this(aName, aMethodName, "");
    }

    public CurveProblemDefinition(String aName, String aMethodName, String aDescription) {
        super();
        FName = aName;
        FMethodName = aMethodName;
        FDescription = aDescription;
    }

    public String getName() {
        return FName;
    }

    public String getDescription() {
        return FDescription;
    }

    public String getMethodName() {
        return FMethodName;
    }

}
