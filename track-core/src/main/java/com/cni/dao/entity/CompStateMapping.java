package com.cni.dao.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class CompStateMapping implements Serializable {


    private String myState;
    private String compName;
    private String exception;
    private List<String> constStates;
    private List<String> regex;



    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMyState() {
        return myState;
    }

    public void setMyState(String myState) {
        this.myState = myState;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public List<String> getConstStates() {
        return constStates;
    }

    public void setConstStates(List<String> constStates) {
        this.constStates = constStates;
    }

    public List<String> getRegex() {
        return regex;
    }

    public void setRegex(List<String> regex) {
        this.regex = regex;
    }


}
