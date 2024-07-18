package com.parser.Parser.parser.model;

import java.math.BigDecimal;

public class ReferenceData {
    private String refkey1;
    private String refdata1;
    private String refkey2;
    private String refdata2;
    private String refdata3;
    private BigDecimal refdata4;

    public ReferenceData(){}

    public ReferenceData(String refkey1, String refdata1, String refkey2, String refdata2, String refdata3, BigDecimal refdata4) {
        this.refkey1 = refkey1;
        this.refdata1 = refdata1;
        this.refkey2 = refkey2;
        this.refdata2 = refdata2;
        this.refdata3 = refdata3;
        this.refdata4 = refdata4;
    }

    public String getRefkey1() {
        return refkey1;
    }

    public void setRefkey1(String refkey1) {
        this.refkey1 = refkey1;
    }

    public String getRefdata1() {
        return refdata1;
    }

    public void setRefdata1(String refdata1) {
        this.refdata1 = refdata1;
    }

    public String getRefkey2() {
        return refkey2;
    }

    public void setRefkey2(String refkey2) {
        this.refkey2 = refkey2;
    }

    public String getRefdata2() {
        return refdata2;
    }

    public void setRefdata2(String refdata2) {
        this.refdata2 = refdata2;
    }

    public String getRefdata3() {
        return refdata3;
    }

    public void setRefdata3(String refdata3) {
        this.refdata3 = refdata3;
    }

    public BigDecimal getRefdata4() {
        return refdata4;
    }

    public void setRefdata4(BigDecimal refdata4) {
        this.refdata4 = refdata4;
    }

    public static interface Refkey1Step {
        Refdata1Step withRefkey1(String refkey1);
    }

    public static interface Refdata1Step {
        Refkey2Step withRefdata1(String refdata1);
    }

    public static interface Refkey2Step {
        Refdata2Step withRefkey2(String refkey2);
    }

    public static interface Refdata2Step {
        Refdata3Step withRefdata2(String refdata2);
    }

    public static interface Refdata3Step {
        Refdata4Step withRefdata3(String refdata3);
    }

    public static interface Refdata4Step {
        BuildStep withRefdata4(BigDecimal refdata4);
    }

    public static interface BuildStep {
        ReferenceData build();
    }


    public static class Builder implements Refkey1Step, Refdata1Step, Refkey2Step, Refdata2Step, Refdata3Step, Refdata4Step, BuildStep {
        private String refkey1;
        private String refdata1;
        private String refkey2;
        private String refdata2;
        private String refdata3;
        private BigDecimal refdata4;

        private Builder() {
        }

        public static Refkey1Step referenceData() {
            return new Builder();
        }

        @Override
        public Refdata1Step withRefkey1(String refkey1) {
            this.refkey1 = refkey1;
            return this;
        }

        @Override
        public Refkey2Step withRefdata1(String refdata1) {
            this.refdata1 = refdata1;
            return this;
        }

        @Override
        public Refdata2Step withRefkey2(String refkey2) {
            this.refkey2 = refkey2;
            return this;
        }

        @Override
        public Refdata3Step withRefdata2(String refdata2) {
            this.refdata2 = refdata2;
            return this;
        }

        @Override
        public Refdata4Step withRefdata3(String refdata3) {
            this.refdata3 = refdata3;
            return this;
        }

        @Override
        public BuildStep withRefdata4(BigDecimal refdata4) {
            this.refdata4 = refdata4;
            return this;
        }

        @Override
        public ReferenceData build() {
            return new ReferenceData(
                    this.refkey1,
                    this.refdata1,
                    this.refkey2,
                    this.refdata2,
                    this.refdata3,
                    this.refdata4
            );
        }
    }
}

