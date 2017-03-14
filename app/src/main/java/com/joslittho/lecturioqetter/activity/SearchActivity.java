package com.joslittho.lecturioqetter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joslittho.lecturioqetter.R;
import com.joslittho.lecturioqetter.data.Utility;
import com.joslittho.lecturioqetter.data.remote.FetchQuestionsTask;
import com.joslittho.lecturioqetter.event.FetchedQuestionsEvent;
import com.joslittho.lecturioqetter.ui.QuestionsAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * The search activity
 */
// begin activity SearchActivity
public class SearchActivity extends AppCompatActivity {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */

    /**
     * The logger.
     */
    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    
    /* VARIABLES */

    @BindView( R.id.search_edit_text_input )
    public EditText mInputEditText; // ditto

    @BindView( R.id.search_recycler_questions )
    public RecyclerView mQuestionsRecyclerView; // ditto

    @BindView( R.id.search_progress_bar )
    public ProgressBar mProgressBar; // ditto

    /* TextViews */

    @BindView( R.id.search_text_status )
    public TextView mStatusTextView; // ditto

    /* CONSTRUCTOR */
    
    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */

    @Override
    // begin onCreate
    protected void onCreate( Bundle savedInstanceState ) {

        // 0. super stuff
        // 1. use the correct layout
        // 2. bind views
        // 3. set up recycler

        // 0. super stuff

        super.onCreate( savedInstanceState );

        // 1. use the correct layout

        setContentView( R.layout.activity_search );

        // 2. bind views

        ButterKnife.bind( this );

        // 3. set up recycler

        mQuestionsRecyclerView.setLayoutManager( new LinearLayoutManager( this ) );

    } // end onCreate

    @Override
    // begin onStart
    protected void onStart() {

        // 0. super stuff
        // 1. register for events

        // 0. super stuff

        super.onStart();

        // 1. register for events

        EventBus.getDefault().register( this );

    } // end onStart

    @Override
    // begin onStop
    protected void onStop() {

        // 0. super stuff
        // 1. unregister for events

        // 0. super stuff

        super.onStop();

        // 1. unregister for events

        EventBus.getDefault().unregister( this );

    } // end onStop

    /* Other Methods */

    @OnClick ( R.id.search_button_submit )
    public void onClickSubmit( View view ) {

        // 0. if the input text is empty, tell the user
        // 1. otherwise show loading and call the fetching questions task

        // 0. if the input text is empty, tell the user

        String inputText = String.valueOf( mInputEditText.getText() );

        if ( TextUtils.isEmpty( inputText ) ) {

            mStatusTextView.setText( R.string.message_info_enter_topic );

            mProgressBar.setVisibility( View.GONE );

            mQuestionsRecyclerView.setVisibility( View.GONE );

        }

        // 1. otherwise show loading and call fetching questions task

        else {

            mStatusTextView.setVisibility( View.GONE );

            mProgressBar.setVisibility( View.VISIBLE );

            mQuestionsRecyclerView.setVisibility( View.GONE );

            new FetchQuestionsTask().execute( Utility.formattedQuery( this, inputText ) );

        }

    }

    /** Catch the {@link com.joslittho.lecturioqetter.event.FetchedQuestionsEvent} */
    @Subscribe( threadMode = ThreadMode.MAIN )
    // begin method onFetchedQuestions
    public void onFetchedQuestions( FetchedQuestionsEvent fetchedQuestionsEvent ) {

        // 0. set the recycler to use the fetched questions
        // 1. show the recycler and nothing else

        // 0. set the recycler to use the fetched questions

        mQuestionsRecyclerView.setAdapter(
                new QuestionsAdapter( this, fetchedQuestionsEvent.getQuestions() ) );

        // 1. show the recycler and nothing else

        mStatusTextView.setVisibility( View.GONE );

        mProgressBar.setVisibility( View.GONE );

        mQuestionsRecyclerView.setVisibility( View.VISIBLE );

    } // end method onFetchedQuestions

} // end activity SearchActivity
