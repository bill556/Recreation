package com.bill.recreation.mvp.entity;

import java.util.List;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class GirlData {
    private boolean isError;
    private List<PhotoGirl> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setResults(List<PhotoGirl> results) {
        this.results = results;
    }

    public List<PhotoGirl> getResults() {
        return results;
    }
}
