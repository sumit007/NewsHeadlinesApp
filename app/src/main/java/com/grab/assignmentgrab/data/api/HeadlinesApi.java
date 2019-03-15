package com.grab.assignmentgrab.data.api;

import com.grab.assignmentgrab.data.model.TopHeadLines;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sumit on 15/03/19.
 */


public interface HeadlinesApi {

    String API_KEY = "0688e3c0cada43d69affb78a9339271e";

    @GET("top-headlines?country=us&apiKey=0688e3c0cada43d69affb78a9339271e")
    Single<TopHeadLines> getHeadLines();

}
