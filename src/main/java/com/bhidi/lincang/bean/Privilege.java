package com.bhidi.lincang.bean;

public class Privilege {
    private int id;
    private String module;
    private String classification;
    private String subclassification;
    private String authdescription;

    public Privilege() {
    }

    public Privilege(int id, String module, String classification, String subclassification, String authdescription) {
        this.id = id;
        this.module = module;
        this.classification = classification;
        this.subclassification = subclassification;
        this.authdescription = authdescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSubclassification() {
        return subclassification;
    }

    public void setSubclassification(String subclassification) {
        this.subclassification = subclassification;
    }

    public String getAuthdescription() {
        return authdescription;
    }

    public void setAuthdescription(String authdescription) {
        this.authdescription = authdescription;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", module='" + module + '\'' +
                ", classification='" + classification + '\'' +
                ", subclassification='" + subclassification + '\'' +
                ", authdescription='" + authdescription + '\'' +
                '}';
    }
}
