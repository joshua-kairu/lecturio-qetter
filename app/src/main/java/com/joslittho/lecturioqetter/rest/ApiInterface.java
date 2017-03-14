package com.joslittho.lecturioqetter.rest;

import com.joslittho.lecturioqetter.data.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API interface to act as an endpoint
 */
// begin interface ApiInterface
public interface ApiInterface {

    /* VARIABLES */
    
    /* METHODS */

    @GET( "question/lecturio_com" )
    Call< Data > getData( @Query( "q" ) String searchQuery );

} // end interface ApiInterface
