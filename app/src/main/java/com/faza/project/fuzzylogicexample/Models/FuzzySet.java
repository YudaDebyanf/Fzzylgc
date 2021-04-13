package com.faza.project.fuzzylogicexample.Models;

/**
 * Dibuat oleh Faza Zulfika Permana Putra
 */

public class FuzzySet {
    private Boolean isTrue;
    private Integer max, min, type; // type : 1 = linear, 2 = naik, 3 = turun
    private Double membership;
    private String name;

    public FuzzySet(Integer max, Integer min, Integer type, String name) {
        this.max = max;
        this.min = min;
        this.type = type;
        this.name = name;
    }

    public FuzzySet() {

    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getMembership() {
        return membership;
    }

    public void setMembership(Double membership) {
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
