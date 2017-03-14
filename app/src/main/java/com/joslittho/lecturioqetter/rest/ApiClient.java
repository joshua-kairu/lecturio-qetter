package com.joslittho.lecturioqetter.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * API client for {@link retrofit2.Retrofit}
 */
// begin class ApiClient
public class ApiClient {

    /* CONSTANTS */
    
    /* Integers */

    /* Retrofits */

    private static Retrofit retrofit = null;

    /* Strings */

    public static final String BASE_URL = "https://www.lecturio.de/api/en/v7/android/search/";

    // the base url

    /* VARIABLES */
    
    /* CONSTRUCTOR */
    
    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */
    
    /* Other Methods */

    /* statics */

    // begin method getClient
    public static Retrofit getClient() {

        // 0. if there isn't a retrofit instance, return one

        // 0. if there isn't a retrofit instance, return one

        if ( retrofit == null ) {

            retrofit = new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build();
        }

        return retrofit;

    } // end method getClient


} // end class ApiClient
