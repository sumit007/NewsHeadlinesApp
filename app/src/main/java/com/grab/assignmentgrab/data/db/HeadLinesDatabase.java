package com.grab.assignmentgrab.data.db;

/**
 * Created by Sumit on 15/03/19.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.grab.assignmentgrab.data.model.TopHeadLines;

@Database(entities = {TopHeadLines.Article.class}, version = 1, exportSchema = false)
public abstract class HeadLinesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "headlines_database";

    public abstract HeadlineDAO getHeadlineDAO();

}
