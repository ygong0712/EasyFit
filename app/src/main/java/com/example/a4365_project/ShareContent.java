package com.example.a4365_project;

public class ShareContent {
    int bc;
    int lc;
    int dc;
    int bp;
    int lp;
    int dp;
    int bf;
    int lf;
    int df;
    private String username;
    public ShareContent(String username, int bc, int lc, int dc, int bp, int lp, int dp, int bf, int lf, int df) {
        this.bc = bc;
        this.lc = lc;
        this.bp = bp;
        this.dc = dc;
        this.lp = lp;
        this.dp = dp;
        this.bf = bf;
        this.lf = lf;
        this.df = df;
        this.username = username;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) {this.username = username;}
    public int getBc() {
        return bc;
    }

    public void setBc(int bc) {
        this.bc = bc;
    }

    public int getLc() {
        return lc;
    }

    public void setLc(int lc) {
        this.lc = lc;
    }

    public int getDc() {
        return dc;
    }

    public void setDc(int dc) {
        this.dc = dc;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }

    public int getLf() {
        return lf;
    }

    public void setLf(int lf) {
        this.lf = lf;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }
}
