package com.grab.assignmentgrab.data.api;

import com.grab.assignmentgrab.data.model.TopHeadLines;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Sumit on 15/03/19.
 */


public class NetworkRepository {

    private final HeadlinesApi headlinesApi;

    @Inject
    public NetworkRepository(HeadlinesApi headlinesApi) {
        this.headlinesApi = headlinesApi;
    }

    public Single<TopHeadLines> getTopHeadLines() {
        return headlinesApi.getHeadLines("US", "Business");
    }
}
