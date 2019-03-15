package com.grab.assignmentgrab.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.grab.assignmentgrab.data.model.TopHeadLines;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailsViewModel extends ViewModel {

    private CompositeDisposable disposable;

    private final MutableLiveData<TopHeadLines.Article> selectedRepo = new MutableLiveData<>();

    public LiveData<TopHeadLines.Article> getSelectedRepo() {
        return selectedRepo;
    }

    @Inject
    public DetailsViewModel() {
        disposable = new CompositeDisposable();
    }

    public void setSelectedArticle(TopHeadLines.Article article) {
        selectedRepo.setValue(article);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
