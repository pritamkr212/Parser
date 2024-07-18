package com.parser.Parser.parser.model;

import java.math.BigDecimal;

public class OutputData {
    private String outfield1;
    private String outfield2;
    private String outfield3;
    private BigDecimal outfield4;
    private BigDecimal outfield5;

    public OutputData(){}

    public OutputData(String outfield1, String outfield2, String outfield3, BigDecimal outfield4, BigDecimal outfield5) {
        this.outfield1 = outfield1;
        this.outfield2 = outfield2;
        this.outfield3 = outfield3;
        this.outfield4 = outfield4;
        this.outfield5 = outfield5;
    }

    public String getOutfield1() {
        return outfield1;
    }

    public void setOutfield1(String outfield1) {
        this.outfield1 = outfield1;
    }

    public String getOutfield2() {
        return outfield2;
    }

    public void setOutfield2(String outfield2) {
        this.outfield2 = outfield2;
    }

    public String getOutfield3() {
        return outfield3;
    }

    public void setOutfield3(String outfield3) {
        this.outfield3 = outfield3;
    }

    public BigDecimal getOutfield4() {
        return outfield4;
    }

    public void setOutfield4(BigDecimal outfield4) {
        this.outfield4 = outfield4;
    }

    public BigDecimal getOutfield5() {
        return outfield5;
    }

    public void setOutfield5(BigDecimal outfield5) {
        this.outfield5 = outfield5;
    }

    public static interface Outfield1Step {
        Outfield2Step withOutfield1(String outfield1);
    }

    public static interface Outfield2Step {
        Outfield3Step withOutfield2(String outfield2);
    }

    public static interface Outfield3Step {
        Outfield4Step withOutfield3(String outfield3);
    }

    public static interface Outfield4Step {
        Outfield5Step withOutfield4(BigDecimal outfield4);
    }

    public static interface Outfield5Step {
        BuildStep withOutfield5(BigDecimal outfield5);
    }

    public static interface BuildStep {
        OutputData build();
    }


    public static class Builder implements Outfield1Step, Outfield2Step, Outfield3Step, Outfield4Step, Outfield5Step, BuildStep {
        private String outfield1;
        private String outfield2;
        private String outfield3;
        private BigDecimal outfield4;
        private BigDecimal outfield5;

        private Builder() {
        }

        public static Outfield1Step outputData() {
            return new Builder();
        }

        @Override
        public Outfield2Step withOutfield1(String outfield1) {
            this.outfield1 = outfield1;
            return this;
        }

        @Override
        public Outfield3Step withOutfield2(String outfield2) {
            this.outfield2 = outfield2;
            return this;
        }

        @Override
        public Outfield4Step withOutfield3(String outfield3) {
            this.outfield3 = outfield3;
            return this;
        }

        @Override
        public Outfield5Step withOutfield4(BigDecimal outfield4) {
            this.outfield4 = outfield4;
            return this;
        }

        @Override
        public BuildStep withOutfield5(BigDecimal outfield5) {
            this.outfield5 = outfield5;
            return this;
        }

        @Override
        public OutputData build() {
            return new OutputData(
                    this.outfield1,
                    this.outfield2,
                    this.outfield3,
                    this.outfield4,
                    this.outfield5
            );
        }
    }
}