package com.joslittho.lecturioqetter.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joslittho.lecturioqetter.R;
import com.joslittho.lecturioqetter.data.model.Question;

import java.util.List;

/**
 *
 */
// begin class QuestionsAdapter
public class QuestionsAdapter extends RecyclerView.Adapter< QuestionViewHolder > {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /* VARIABLES */

    private Context mContext; // ditto

    private List< Question > mQuestions; // ditto
    
    /* CONSTRUCTOR */

    public QuestionsAdapter( Context context, List< Question > questions ) {
        this.mContext = context;
        this.mQuestions = questions;
    }

    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */

    @Override
    public QuestionViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {

        // 0. inflate the individual question layout and return it

        // 0. inflate the individual question layout and return it

        View view = LayoutInflater.from( mContext ).inflate( R.layout.question_item, parent, false );

        return new QuestionViewHolder( mContext, mQuestions, view );

    }

    @Override
    public void onBindViewHolder( QuestionViewHolder holder, int position ) {

        // 0. show the question at this position of the array

        // 0. show the question at this position of the array

        holder.mTitleTextView.setText( mQuestions.get( position ).getTitle() );

    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    /* Other Methods */

} // end class QuestionsAdapter
