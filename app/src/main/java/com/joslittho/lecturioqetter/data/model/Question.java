package com.joslittho.lecturioqetter.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A question
 */
// begin class Question
public class Question {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /* VARIABLES */

    /* Lists */

    private List<Answer> mAnswers;

    /* Strings */

    private String mTitle;

    /* CONSTRUCTOR */

    /* Default constructor */
    public Question( List< Answer > answers, String title ) {
        this.mAnswers = answers;
        this.mTitle = title;
    }

    /* METHODS */
    
    /* Getters and Setters */

    public List< Answer > getAnswers() {
        return mAnswers;
    }

    public void setAnswers( List< Answer > answers ) {
        this.mAnswers = answers;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle( String title ) {
        this.mTitle = title;
    }

    /* Overrides */
    
    /* Other Methods */

} // end class Question
