package org.example;

import java.util.Objects;

public class DataDbo {
    private String str;
    private int num;

    // for jackson deserialization
    public DataDbo() {}

    public DataDbo(String str, int num) {
        this.str = str;
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "{" +
                "str='" + str + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDbo dataDbo = (DataDbo) o;
        return num == dataDbo.num && Objects.equals(str, dataDbo.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, num);
    }
}
