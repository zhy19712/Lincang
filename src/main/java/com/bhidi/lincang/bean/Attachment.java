package com.bhidi.lincang.bean;

public class Attachment {
    private int id;
    private String name;
    private String path;
    private Long size;

    public Attachment() {
    }

    public Attachment(int id,String name, String path, Long size) {
        this.id = id;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
