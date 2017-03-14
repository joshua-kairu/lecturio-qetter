package com.joslittho.lecturioqetter.data;

import android.content.Context;

import com.joslittho.lecturioqetter.R;
import com.joslittho.lecturioqetter.data.model.Answer;
import com.joslittho.lecturioqetter.data.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class
 */
// begin class Utility
public class Utility {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /* VARIABLES */
    
    /* CONSTRUCTOR */
    
    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */
    
    /* Other Methods */

    /* statics */

    /**
     * Formats a query so that the Lecturio API can understand it.
     *
     * In particular, for a query "Anatomy", the Lecturio API wants "%Anatomy%"
     */
    public static String formattedQuery( Context context, String query ) {
        return context.getString( R.string.format_query, query );
    }

    /** 
     * Gets an array of answers in String from for a given
     * {@link com.joslittho.lecturioqetter.data.model.Question}.
     * */
    public static String[] getAnswersForQuestion ( Question question ) {
        
        // 0. have a list ready
        // 1. for each answer in the question
        // 1a. put it in the ready list in string form
        // last. return the list as an array
        
        // 0. have a list ready
        
        List< String > answers = new ArrayList<>( question.getAnswers().size() );
        
        // 1. for each answer in the question

        // 1a. put it in the ready list in string form

        for ( Answer answer :
                question.getAnswers() ) {
            answers.add( answer.getTitle() );
        }

        // last. return the list

        return answers.toArray( new String[ answers.size() ] );
        
    }

} // end class Utility
