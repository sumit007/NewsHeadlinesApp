package com.grab.assignmentgrab.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.grab.assignmentgrab.data.db.HeadLinesDatabase;
import com.grab.assignmentgrab.data.db.HeadlineDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sumit on 15/03/19.
 */


@Module
public class HeadLineDbModule {

    @Singleton
    @Provides
    public HeadLinesDatabase provideHeadLinesDatabase(Context context) {
        return Room.databaseBuilder(context,
                HeadLinesDatabase.class, HeadLinesDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public HeadlineDAO provideHeadLinesDao(HeadLinesDatabase tvMazeDatabase) {
        return tvMazeDatabase.getHeadlineDAO();
    }

}
