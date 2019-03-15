package com.grab.assignmentgrab.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.grab.assignmentgrab.data.model.TopHeadLines;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Sumit on 15/03/19.
 */


@Dao
public interface HeadlineDAO {

    @Query("SELECT * FROM news_article")
    Single<List<TopHeadLines.Article>> getTopHeadLines();

    @Query("SELECT COUNT(*) from news_article")
    int countUsers();

    @Insert
    void insertAll(TopHeadLines.Article... articles);

    @Delete
    void delete(TopHeadLines.Article user);
}
