package com.grab.assignmentgrab.data.db;

import com.grab.assignmentgrab.data.model.TopHeadLines;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Sumit on 15/03/19.
 */


public class DatabaseRepository {
    private final HeadlineDAO headlineDAO;

    @Inject
    public DatabaseRepository(HeadlineDAO headlineDAO) {
        this.headlineDAO = headlineDAO;
    }

    public Single<List<TopHeadLines.Article>> getTopHeadLines() {
        return headlineDAO.getTopHeadLines();
    }

    public void insertArticleIntoDb(TopHeadLines.Article show) {

        headlineDAO.insertAll(show);
    }
}
