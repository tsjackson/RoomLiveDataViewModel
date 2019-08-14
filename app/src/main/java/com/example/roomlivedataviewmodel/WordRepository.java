package com.example.roomlivedataviewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//Repository implements the logic for deciding
// whether to fetch data from a network or use
// results cached in the local database.

public class WordRepository {
    private  WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    //constructor that gets a handle to the DB
    // and initializes the member variables

    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    // This is called a wrapper, this ones job is to return a cached
    // words as LiveData.
    LiveData<List<Word>> getAllWords(){return mAllWords;}

    public void insert (Word word){
        new InsertAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncTaskDao;

        InsertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Word...params){
            // this is inserting stuff into the database
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
