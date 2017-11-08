package com.bhidi.lincang.bean;

public class RolePrivilege {
    private int id;
    private int roleid;
    private int authorithid;

    public RolePrivilege() {
    }

    public RolePrivilege(int id, int roleid, int authorithid) {
        this.id = id;
        this.roleid = roleid;
        this.authorithid = authorithid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getAuthorithid() {
        return authorithid;
    }

    public void setAuthorithid(int authorithid) {
        this.authorithid = authorithid;
    }

    @Override
    public String toString() {
        return "RolePrivilege{" +
                "id=" + id +
                ", roleid=" + roleid +
                ", authorithid=" + authorithid +
                '}';
    }
}
