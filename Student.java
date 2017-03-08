package amuntest;

public class Student {
    private int uniqueID;
    private String timeStamp;
    private String state;
    private String fName;
    private String lName;
    //private String state;
    private String arSchool;
    private String okSchool;
    private String tnSchool;
    private String laSchool;
    private String msSchool;
    private String txSchool;
    private String moSchool;
    private Boolean headDelegate;
    private String studentDesignation;
    private String countryAssignment;
    private String committeeAssignment;
    private Boolean addStudent;

    public Student(int id, String ts,String fn, String ln, String st, String ar, 
            String ok, String tn, String la, String ms, String tx, String mo, 
            Boolean hd, String sd, String cu, String cm, Boolean as) {
        
        this.setUniqueID(id);
        this.setTimeStamp(ts);
        this.setState(st);
        this.setfName(fn);
        this.setlName(ln);
        this.setArSchool(ar);
        this.setOkSchool(ok);
        this.setTnSchool(tn);
        this.setLaSchool(la);
        this.setMsSchool(ms);
        this.setTxSchool(tx);
        this.setMoSchool(mo);
        this.setHeadDelegate(hd);
        this.setStudentDesignation(sd);
        this.setCountryAssignment(cu);
        this.setCommitteeAssignment(cm);
        this.setAddStudent(as);
    } // end ctor
    
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }


    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the arSchool
     */
    public String getArSchool() {
        return arSchool;
    }

    /**
     * @param arSchool the arSchool to set
     */
    public void setArSchool(String arSchool) {
        this.arSchool = arSchool;
    }

    /**
     * @return the okSchool
     */
    public String getOkSchool() {
        return okSchool;
    }

    /**
     * @param okSchool the okSchool to set
     */
    public void setOkSchool(String okSchool) {
        this.okSchool = okSchool;
    }

    /**
     * @return the tnSchool
     */
    public String getTnSchool() {
        return tnSchool;
    }

    /**
     * @param tnSchool the tnSchool to set
     */
    public void setTnSchool(String tnSchool) {
        this.tnSchool = tnSchool;
    }

    /**
     * @return the laSchool
     */
    public String getLaSchool() {
        return laSchool;
    }

    /**
     * @param laSchool the laSchool to set
     */
    public void setLaSchool(String laSchool) {
        this.laSchool = laSchool;
    }

    /**
     * @return the msSchool
     */
    public String getMsSchool() {
        return msSchool;
    }

    /**
     * @param msSchool the msSchool to set
     */
    public void setMsSchool(String msSchool) {
        this.msSchool = msSchool;
    }

    /**
     * @return the txSchool
     */
    public String getTxSchool() {
        return txSchool;
    }

    /**
     * @param txSchool the txSchool to set
     */
    public void setTxSchool(String txSchool) {
        this.txSchool = txSchool;
    }

    /**
     * @return the moSchool
     */
    public String getMoSchool() {
        return moSchool;
    }

    /**
     * @param moSchool the moSchool to set
     */
    public void setMoSchool(String moSchool) {
        this.moSchool = moSchool;
    }

    /**
     * @return the headDelegate
     */
    public Boolean getHeadDelegate() {
        return headDelegate;
    }

    /**
     * @param headDelegate the headDelegate to set
     */
    public void setHeadDelegate(Boolean headDelegate) {
        this.headDelegate = headDelegate;
    }

    /**
     * @return the studentDesignation
     */
    public String getStudentDesignation() {
        return studentDesignation;
    }

    /**
     * @param studentDesignation the studentDesignation to set
     */
    public void setStudentDesignation(String studentDesignation) {
        this.studentDesignation = studentDesignation;
    }

    /**
     * @return the countryAssignment
     */
    public String getCountryAssignment() {
        return countryAssignment;
    }

    /**
     * @param countryAssignment the countryAssignment to set
     */
    public void setCountryAssignment(String countryAssignment) {
        this.countryAssignment = countryAssignment;
    }

    /**
     * @return the committeeAssignment
     */
    public String getCommitteeAssignment() {
        return committeeAssignment;
    }

    /**
     * @param committeeAssignment the committeeAssignment to set
     */
    public void setCommitteeAssignment(String committeeAssignment) {
        this.committeeAssignment = committeeAssignment;
    }

    /**
     * @return the addStudent
     */
    public Boolean getAddStudent() {
        return addStudent;
    }

    /**
     * @param addStudent the addStudent to set
     */
    public void setAddStudent(Boolean addStudent) {
        this.addStudent = addStudent;
    }
} // end class Student