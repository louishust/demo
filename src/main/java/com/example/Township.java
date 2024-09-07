package com.example;

class Township {
    private String name;
    private double attr1;
    private double attr2;
    private double attr3;
    private double attr4;
    private double result;

    public double getAttr1() {
        return attr1;
    }

    public void setAttr1(double attr1) {
        this.attr1 = attr1;
    }

    public double getAttr2() {
        return attr2;
    }

    public void setAttr2(double attr2) {
        this.attr2 = attr2;
    }

    public double getAttr3() {
        return attr3;
    }

    public void setAttr3(double attr3) {
        this.attr3 = attr3;
    }

    public double getAttr4() {
        return attr4;
    }

    public void setAttr4(double attr4) {
        this.attr4 = attr4;
    }

    public Township(String name, double attr1, double attr2, double attr3, double attr4) {
        this.name = name;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.attr4 = attr4;
    }

    public void calcResult(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.result = i0 + (this.attr1 - 793) / (49793 - 793) * i1 +
                      i2 + (this.attr2 - 254853) / (23716750 - 254853) * i3 +
                      i4 + (this.attr3 - 5157) / (2474469 - 5157) * i5 +
                      i6 + (this.attr4 - 2652) / (9144511 - 2652) * i7;
    }

    public void calcResult2(double i0, double i1, double i2, double i3, double i4, double i5, double i6, double i7) {
        this.result = i0 + (this.attr1 - 793) / (49793 - 793) * i1 +
                      i2 + (this.attr2 - 254853) / (23716750 - 254853) * i3 +
                      i4 + (this.attr3 - 5157) / (2474469 - 5157) * i5 +
                      i6 + (this.attr4 - 2652) / (9144511 - 2652) * i7;
    }

    public String getName() {
        return name;
    }

    public Double getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Township{" +
                "name='" + name + '\'' +
                ", attr1=" + attr1 +
                ", attr2=" + attr2 +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                ", result=" + result +
                '}';
    }
}
