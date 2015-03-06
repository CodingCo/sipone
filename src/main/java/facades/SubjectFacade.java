package facades;

import webInterface.SubjectFacadeIF;

public class SubjectFacade implements SubjectFacadeIF {

    @Override
    public String getFirstElectiveSubjects() {

        return "{\n"
                + "  err: true,\n"
                + "  title: “Not supported yet”\n"
                + "}";

    }

    @Override
    public String submittedFirstElectiveSubjects(String subjectAsJson) {

        return "{\n"
                + "  err: true,\n"
                + "  title: “Not supported yet”\n"
                + "}";
    }

}
