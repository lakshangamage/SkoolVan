package com.intelligentz.skoolvan.model;

import android.view.View;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

/**
 * Created by Lakshan on 2017-03-18.
 */

public class SearchSet {
    private String from;
    private String school;
    private SearchableSpinner from_spinner;
    private SearchableSpinner to_spinner;

    public SearchSet() {
    }

    public SearchSet(String from, String school) {
        this.from = from;
        this.school = school;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public SearchableSpinner getFrom_spinner() {
        return from_spinner;
    }

    public void setFrom_spinner(SearchableSpinner from_spinner) {
        this.from_spinner = from_spinner;
    }

    public SearchableSpinner getTo_spinner() {
        return to_spinner;
    }

    public void setTo_spinner(SearchableSpinner to_spinner) {
        this.to_spinner = to_spinner;
    }
}
