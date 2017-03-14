package com.joslittho.lecturioqetter.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The response data
 */
// begin class Data
public class Data {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /* VARIABLES */

    /* Lists */

    private List<Question> mQuestions;
    
    /* CONSTRUCTOR */

    /* Default constructor */
    public Data( List< Question > questions ) {
        this.mQuestions = questions;
    }

    /* METHODS */
    
    /* Getters and Setters */

    public List< Question > getQuestions() {
        return mQuestions;
    }

    public void setQuestions( List< Question > questions ) {
        this.mQuestions = questions;
    }

    /* Overrides */
    
    /* Other Methods */

} // end class Data
