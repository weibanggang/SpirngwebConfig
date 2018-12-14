package com.wbg.springJavaConfig.entity;

public class Admin {
    private int aId;
    private String aAccount;
    private String aPassword;
    private String aRank;

    public Admin(int aId, String aAccount, String aPassword, String aRank) {
        this.aId = aId;
        this.aAccount = aAccount;
        this.aPassword = aPassword;
        this.aRank = aRank;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getaAccount() {
        return aAccount;
    }

    public void setaAccount(String aAccount) {
        this.aAccount = aAccount;
    }

    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    public String getaRank() {
        return aRank;
    }

    public void setaRank(String aRank) {
        this.aRank = aRank;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + aId +
                ", aAccount='" + aAccount + '\'' +
                ", aPassword='" + aPassword + '\'' +
                ", aRank='" + aRank + '\'' +
                '}';
    }
}
