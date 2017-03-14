package com.joslittho.lecturioqetter.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joslittho.lecturioqetter.R;
import com.joslittho.lecturioqetter.data.Utility;
import com.joslittho.lecturioqetter.data.model.Question;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * {@link android.support.v7.widget.RecyclerView.ViewHolder} for the questions
 */
// begin class QuestionViewHolder
public class QuestionViewHolder extends RecyclerView.ViewHolder {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /* VARIABLES */

    private Context mContext;

    private List< Question > mQuestions;

    @BindView( R.id.question_textview_title )
    public TextView mTitleTextView; // ditto

    /* CONSTRUCTOR */

    public QuestionViewHolder( Context context, List<Question> questions, View itemView ) {

        // 0. super stuff
        // 1. bind views
        // 2. initialize context
        // 3. initialize list

        // 0. super stuff

        super( itemView );

        // 1. bind views

        ButterKnife.bind( this, itemView );

        // 2. initialize context

        mContext = context;

        // 3. initialize list

        mQuestions = questions;

    }

    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */
    
    /* Other Methods */

    @OnClick( R.id.question_textview_title )
    // begin onQuestionClick
    public void onQuestionClick( View view ) {
        Log.e( "QVH", "clicked" );

        AlertDialog.Builder builder = new AlertDialog.Builder( mContext );

        final String[] answers =
                Utility.getAnswersForQuestion( mQuestions.get( getAdapterPosition() ) );

        builder.setTitle( mQuestions.get( getAdapterPosition() ).getTitle() )
                .setItems( answers, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        Toast.makeText( mContext, "Clicked " + answers[ i ] , Toast.LENGTH_SHORT )
                                .show();
                    }
                } );

        builder.create().show();

    } // end onQuestionClick

} // end class QuestionViewHolder
