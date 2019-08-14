package com.example.roomlivedataviewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    //constructor that gets a reference to the WordRepository and gets the list of all words from the WordRepository
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //a "getter" method that gets all the words. This completely hides the implementation from the UI.
    LiveData<List<Word>> getAllWords() { return mAllWords; }

    //wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is completely hidden from the UI.

    public void insert(Word word) { mRepository.insert(word); }
}
