package com.joslittho.lecturioqetter.data.remote;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.joslittho.lecturioqetter.data.model.Answer;
import com.joslittho.lecturioqetter.event.FetchedQuestionsEvent;
import com.joslittho.lecturioqetter.data.model.Question;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link AsyncTask} to fetch the questions from Lecturio.
 *
 * The parameters should include a string containing the topic
 *
 * Returns an array of questions gotten from Lecturio.
 * */
// begin class FetchMovieTask
public class FetchQuestionsTask extends AsyncTask< String, Void, List< Question > > {

    /* CONSTANTS */

    /* Integers */

    /* Strings */

    /**
     * The logger.
     */
    private static final String LOG_TAG = FetchQuestionsTask.class.getSimpleName();

    /* VARIABLES */

    /* CONSTRUCTOR */

    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */

    @Override
    // begin doInBackground
    protected List< Question > doInBackground( String... params ) {

        // 0. if there is no topic, just stop
        // 1. initialize
        // 1a. the http connection
        // 1b. the buffered reader
        // 1c. the string that will store the received JSON
        // 2. construct the url to fetch
        // 3. create a GET request to the url
        // 4. connect to the url
        // 5. buffer read from the url to a string
        // 5a. if there is nothing to read, stop
        // 6. sanitize the read string into a string builder for logging
        // 7. if the string builder is empty, stop
        // 8. return an array of the gotten movies
        // e0. io, log, stop
        // e1. json, log, stop
        // 9. finally
        // 9a. disconnect the url
        // 9b. close the buffered reader
        // e0. io, log

        // 0. if there is no topic, just stop

        if ( params.length == 0 ) { return null; }

        // 1. initialize

        // 1a. the http connection

        HttpURLConnection httpURLConnection = null;

        // 1b. the buffered reader

        // Wraps an existing Reader and buffers the input. Expensive interaction with the
        // underlying reader is minimized, since most (smaller) requests can be satisfied by
        // accessing the buffer alone
        BufferedReader bufferedReader = null;

        // 1c. the string that will store the received JSON

        String questionsJSONString;

        // begin try to finish network work
        try {

            // 2. construct the url to fetch

            // http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]
            // https://www.lecturio.de/api/en/v7/android/search/question/lecturio_com?q=​%SEARCH_TERM%
            // Where ​%SEARCH_TERM%​ is the term from the search input

            final String BASE_URL =
                    "https://www.lecturio.de/api/en/v7/android/search/question/lecturio_com";
            final String QUERY_PARAMETER = "q";

            Uri builtUri = Uri.parse( BASE_URL ).buildUpon()
                    .appendQueryParameter( QUERY_PARAMETER, params[ 0 ] )
                    .build();

            URL movieUrl = new URL( builtUri.toString() );

            // 3. create a GET request to the url

            // openConnection - Returns a new connection to the resource referred to by this URL.
            httpURLConnection = ( HttpURLConnection ) movieUrl.openConnection();

            httpURLConnection.setRequestMethod( "GET" );

            // 4. connect to the url

            httpURLConnection.connect();

            // 5. buffer read from the url to a string

            InputStream inputStream = httpURLConnection.getInputStream();

            // 5a. if there is nothing to read, stop

            if ( inputStream == null ) {
                Log.e( LOG_TAG, "doInBackground: InputStream is null" );
                return null;
            }

            // InputStreamReader - Constructs a new InputStreamReader on the InputStream in.
            bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );

            // 6. sanitize the read string into a string builder for logging

            // StringBuilder - A modifiable sequence of characters for use in creating strings.
            //  This class is intended as a direct replacement of StringBuffer for
            //  non-concurrent use; unlike StringBuffer this class is not synchronized.
            StringBuilder stringBuilder = new StringBuilder();

            String lineRead;

            // while to read lines from the buffered reader
            // append a newline for logging
            while ( ( lineRead = bufferedReader.readLine() ) != null ) {
                stringBuilder.append( lineRead ).append( "\n" );
            }

            // 7. if the string builder is empty, stop

            if ( stringBuilder.length() == 0 ){
                Log.e( LOG_TAG, "doInBackground: stringBuilder is empty." );
            }

            // 8. return an array of the gotten movies

            questionsJSONString = stringBuilder.toString();

            return getQuestionDataFromJSON( questionsJSONString );

        }  // end try to finish network work

        // e0. io, log, stop

        catch ( IOException e ) { Log.e( LOG_TAG, "doInBackground: ", e ); return null; }

        // e1. json, log, stop

        catch ( JSONException e ) { Log.e( LOG_TAG, "doInBackground: ", e ); return null; }

        // 9. finally

        // begin finally
        finally {

            // 9a. disconnect the url

            if ( httpURLConnection != null ) { httpURLConnection.disconnect(); }

            // 9b. close the buffered reader

            // begin if there is a buffered reader
            if ( bufferedReader != null ) {

                try {
                    bufferedReader.close();
                }

                // e0. io, log

                catch ( IOException e ) {
                    Log.e( LOG_TAG, "doInBackground: Error closing stream", e );
                }

            } // end if there is a buffered reader

        } // end finally

    } // end doInBackground

    @Override
    // begin onPostExecute
    protected void onPostExecute( List< Question > fetchedQuestions ) {

        // 0. super stuff
        // 1. call the finished questions event

        // 0. super stuff

        super.onPostExecute( fetchedQuestions );

        // 1. call the finished questions event

        EventBus.getDefault().post( new FetchedQuestionsEvent( fetchedQuestions ) );

    } // end onPostExecute

    /* Other Methods */

    /**
         * Helper method to get questions data from a JSON string.
     *
     * @param questionsJSONString The string having the questions JSON
     *
     * @return An {@link List} of {@link Question} objects contained in the JSON
     * */
    // begin method getQuestionDataFromJSON
    private List< Question > getQuestionDataFromJSON( String questionsJSONString )
            throws JSONException {

        /*

        Lecturio JSON looks like this:

        {
            "statusCode": 200,
            "error": "",
            "rawJSON": "",
            "data": {
            "​questions​": [
            {
            "id": 22648,
            ​ "title": "How many chambers are there in the human heart?",
            "time": 501,
            "image": null,
            "type": "singleChoice",
            "orderNumber": 1,
            }
            }
            "userAnswerState": -1,
            "​answers​": [
            {
            "id": 86247,
            "​title​": "2",
            "explanation": null,
            "correct": false
            },
            {
            "id": 86246,
            "​title​": "3",
            "explanation": null,
            "correct": false
            },
            {
            "id": 86248,
            "​title​": "5",
            "explanation": null,
            "correct": false
            },
            {
            "id": 86245,
            "title": "4",
            "explanation": null,
            "correct": true
            }
            ],
            "urlCheck": "https://www.lecturio.de/api/en/v6/html5/questions/save-answers.json",
            "lectureNT": "anatomy-of-the-heart"
            }
            "questionsCnt": 933,
            "questionsFreeCnt": 125,
            "questionsFreeConditionalCnt": 117,
            "questionsPaidCnt": 691,
            "questionsPaidConditionalCnt": 0

        What to read​:

        data -> questions (the whole array)
        for each question, read the ​title​ and the ​answers

        */

        // 0. initialize the names of JSON objects to extract
        // 0a. data
        // 0b. questions array
        // 0c. question title
        // 0d. question answers array
        // 0e. question answers title
        // 1. extract data from JSON
        // 1a. get the data object from JSON
        // 1b. get the questions list from JSON
        // 1c. have an array list of questions matching the JSON list
        // 1c0. get a question JSON item
        // 1c0a. save its title
        // 1c0b. get the answers list from JSON
        // 1c0c. have an array list of answers matching the JSON list
        // 1c0d. get the answer title and save it in the array as answer object
        // 1c0e. add the question title and answers to a question object and save it in the array
        // 2. return the gotten questions
        
        // 1b2. create a question object from it
        // 1b3. add that object to the question array
        // 2. return the gotten question

        // 0. initialize the names of JSON objects to extract

        // 0a. data
        // 0b. questions array
        // 0c. question title
        // 0d. question answers array
        // 0e. question answers title

        final String DATA = "data";
        final String QUESTIONS_ARRAY = "questions";
        final String QUESTION_TITLE = "title";
        final String QUESTION_ANSWERS_ARRAY = "answers";
        final String QUESTION_ANSWERS_TITLE = "title";

        // 1. extract data from JSON
        
        // 1a. get the data object from JSON

        JSONObject movieJsonObject = new JSONObject( questionsJSONString );

        JSONObject dataJsonObject = movieJsonObject.getJSONObject( DATA );
        
        // 1b. get the questions list from JSON

        JSONArray questionsListJsonArray = dataJsonObject.getJSONArray( QUESTIONS_ARRAY );

        // 1c. have an array list of questions matching the JSON list

        List< Question > questions = new ArrayList<>( questionsListJsonArray.length() );

        // 1c0. get a question JSON item

        // begin for through the question JSON items
        for ( int i = 0; i < questionsListJsonArray.length(); i++ ) {

            JSONObject currentQuestionJsonObject = questionsListJsonArray.getJSONObject( i );

            // 1c0a. save its title

            String questionTitle = currentQuestionJsonObject.getString( QUESTION_TITLE );

            // 1c0b. get the answers list from JSON

            Log.d( LOG_TAG, currentQuestionJsonObject.toString() );

            if ( ! currentQuestionJsonObject.has( QUESTION_ANSWERS_ARRAY ) ) { continue; }

            JSONArray answersListJsonArray = currentQuestionJsonObject
                    .getJSONArray( QUESTION_ANSWERS_ARRAY );

            // 1c0c. have an array list of answers matching the JSON list

            List< Answer > answers = new ArrayList<>( answersListJsonArray.length() );

            // 1c0d. get the answer title and save it in the array as answer object

            // begin for through each answer
            for ( int j = 0; j < answersListJsonArray.length(); j++ ) {

                JSONObject currentAnswerJsonObject = answersListJsonArray.getJSONObject( j );

                Log.d( LOG_TAG, String.valueOf( j ) + " " + currentAnswerJsonObject.toString() );
                String answerTitle = currentAnswerJsonObject.getString( QUESTION_ANSWERS_TITLE );

                answers.add( new Answer( answerTitle ) );

            } // end for through each answer

            // 1c0e. add the question title and answers to a question object and save it in the array

            questions.add( new Question( answers, questionTitle ) );

        } // end for through the question JSON items

        // 2. return the gotten questions

        return questions;

    } // end method getQuestionDataFromJSON

    /* INNER CLASSES */

} // end class FetchMovieTask