package com.grab.assignmentgrab.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.grab.assignmentgrab.data.api.NetworkRepository;
import com.grab.assignmentgrab.data.model.TopHeadLines;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private final NetworkRepository networkRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<TopHeadLines.Article>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> headlineLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ListViewModel(NetworkRepository repoRepository) {
        this.networkRepository = repoRepository;
        disposable = new CompositeDisposable();
        fetchHeadLines();
    }

    LiveData<List<TopHeadLines.Article>> getArticles() {
        return repos;
    }
    LiveData<Boolean> getError() {
        return headlineLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchHeadLines() {
        loading.setValue(true);
        disposable.add(networkRepository.getTopHeadLines().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<TopHeadLines>() {
                    @Override
                    public void onSuccess(TopHeadLines value) {
                        headlineLoadError.setValue(false);
                        repos.setValue(value.getArticles());
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        headlineLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
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
