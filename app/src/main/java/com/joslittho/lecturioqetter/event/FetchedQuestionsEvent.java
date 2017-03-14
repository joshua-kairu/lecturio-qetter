package com.joslittho.lecturioqetter.event;

import com.joslittho.lecturioqetter.data.model.Question;

import java.util.List;

/**
 * Event to say we have the questions from the net
 */
// begin class FetchedQuestionsEvent
public class FetchedQuestionsEvent {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /* VARIABLES */

    /* List */

    private List< Question > mQuestions;
    
    /* CONSTRUCTOR */

    public FetchedQuestionsEvent( List< Question > questions ) {
        this.mQuestions = questions;
    }

    /* METHODS */
    
    /* Getters and Setters */

    public List< Question > getQuestions() {
        return mQuestions;
    }

    /* Overrides */
    
    /* Other Methods */

} // end class FetchedQuestionsEvent
