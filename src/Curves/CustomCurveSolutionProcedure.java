package Curves;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public abstract class CustomCurveSolutionProcedure extends NGObject {

    protected ArrayList<CurveProblemDefinition> FSolveProblems;

    protected void DoStartSolve(CurveProblemDefinition aProblem) {

    }

    protected Boolean DoSolve(CurveProblemDefinition aProblem) {
        Boolean res = false;
        try {
            res = (Boolean)getClass().getMethod(aProblem.getMethodName()).invoke(this);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    protected void DoEndSolve(CurveProblemDefinition aProblem) {

    }

    protected CurveProblemDefinition getSolveProblem(String aName) {
        for (CurveProblemDefinition problem : FSolveProblems) {
            if (problem.getName().equals(aName)) {
                return problem;
            }
        }
        return null;
    }

    protected void addSolveProblem(CurveProblemDefinition aProblem) {
        FSolveProblems.add(aProblem);
    }

    public CustomCurveSolutionProcedure() {
        super();
        FSolveProblems = new ArrayList<CurveProblemDefinition>();
    }

    public Boolean Solve(CurveProblemDefinition aProblem) {
        Boolean res = canSolveProblem(aProblem);
        if (res) {
            DoStartSolve(aProblem);
            try {
                res = DoSolve(aProblem);
            }
            finally {
                DoEndSolve(aProblem);
            }
        }
        return res;
    }

    public Boolean canSolveProblem(CurveProblemDefinition aProblem) {
        return getSolveProblem(aProblem.getName()) != null;
    }

}
