package com.bhidi.lincang.bean;

public class KuQuKind {
    private String kuQuName;
    private int numOfKuQu;

    public KuQuKind() {
    }

    public KuQuKind(String kuQuName, int numOfKuQu) {
        this.kuQuName = kuQuName;
        this.numOfKuQu = numOfKuQu;
    }

    public String getKuQuName() {
        return kuQuName;
    }

    public void setKuQuName(String kuQuName) {
        this.kuQuName = kuQuName;
    }

    public int getNumOfKuQu() {
        return numOfKuQu;
    }

    public void setNumOfKuQu(int numOfKuQu) {
        this.numOfKuQu = numOfKuQu;
    }

    @Override
    public String toString() {
        return "KuQuKind{" +
                "kuQuName='" + kuQuName + '\'' +
                ", numOfKuQu=" + numOfKuQu +
                '}';
    }
}
