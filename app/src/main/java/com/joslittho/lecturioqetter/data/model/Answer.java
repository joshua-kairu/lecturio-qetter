package com.joslittho.lecturioqetter.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * An answer
 */
// begin class Answer
public class Answer {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /* VARIABLES */

    /* Primitives */

    /* Strings */

    @SerializedName("title")
    @Expose
    private String mTitle;


    /* CONSTRUCTOR */

    /** Default constructor */
    public Answer( String title ) {
        this.mTitle = title;
    }

    /* METHODS */
    
    /* Getters and Setters */

    public String getTitle() {
        return mTitle;
    }

    public void setTitle( String title ) {
        this.mTitle = title;
    }

    /* Overrides */
    
    /* Other Methods */

} // end class Answer
