package com.bhidi.lincang.bean;

public class Attachment {
    private int id;
    private String aid;
    private String name;
    private String path;
    private String size;

    public Attachment() {
    }

    public Attachment(int id, String aid, String name, String path, String size) {
        this.id = id;
        this.aid = aid;
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", aid='" + aid + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
