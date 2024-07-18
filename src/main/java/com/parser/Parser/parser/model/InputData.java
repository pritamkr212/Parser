package com.parser.Parser.parser.model;


import java.math.BigDecimal;


public class InputData {
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private BigDecimal field5;
    private String refkey1;
    private String refkey2;

    public InputData(){}

    public InputData(String field1, String field2, String field3, String field4, BigDecimal field5, String refkey1, String refkey2) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
        this.refkey1 = refkey1;
        this.refkey2 = refkey2;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public BigDecimal getField5() {
        return field5;
    }

    public void setField5(BigDecimal field5) {
        this.field5 = field5;
    }

    public String getRefkey1() {
        return refkey1;
    }

    public void setRefkey1(String refkey1) {
        this.refkey1 = refkey1;
    }

    public String getRefkey2() {
        return refkey2;
    }

    public void setRefkey2(String refkey2) {
        this.refkey2 = refkey2;
    }

    public static interface Field1Step {
        Field2Step withField1(String field1);
    }

    public static interface Field2Step {
        Field3Step withField2(String field2);
    }

    public static interface Field3Step {
        Field4Step withField3(String field3);
    }

    public static interface Field4Step {
        Field5Step withField4(String field4);
    }

    public static interface Field5Step {
        Refkey1Step withField5(BigDecimal field5);
    }

    public static interface Refkey1Step {
        Refkey2Step withRefkey1(String refkey1);
    }

    public static interface Refkey2Step {
        BuildStep withRefkey2(String refkey2);
    }

    public static interface BuildStep {
        InputData build();
    }


    public static class Builder implements Field1Step, Field2Step, Field3Step, Field4Step, Field5Step, Refkey1Step, Refkey2Step, BuildStep {
        private String field1;
        private String field2;
        private String field3;
        private String field4;
        private BigDecimal field5;
        private String refkey1;
        private String refkey2;

        private Builder() {
        }

        public static Field1Step inputData() {
            return new Builder();
        }

        @Override
        public Field2Step withField1(String field1) {
            this.field1 = field1;
            return this;
        }

        @Override
        public Field3Step withField2(String field2) {
            this.field2 = field2;
            return this;
        }

        @Override
        public Field4Step withField3(String field3) {
            this.field3 = field3;
            return this;
        }

        @Override
        public Field5Step withField4(String field4) {
            this.field4 = field4;
            return this;
        }

        @Override
        public Refkey1Step withField5(BigDecimal field5) {
            this.field5 = field5;
            return this;
        }

        @Override
        public Refkey2Step withRefkey1(String refkey1) {
            this.refkey1 = refkey1;
            return this;
        }

        @Override
        public BuildStep withRefkey2(String refkey2) {
            this.refkey2 = refkey2;
            return this;
        }

        @Override
        public InputData build() {
            return new InputData(
                    this.field1,
                    this.field2,
                    this.field3,
                    this.field4,
                    this.field5,
                    this.refkey1,
                    this.refkey2
            );
        }
    }
}