package com.grab.assignmentgrab.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.grab.assignmentgrab.data.model.TopHeadLines;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class DetailsViewModel extends ViewModel {

    private CompositeDisposable disposable;

    private final MutableLiveData<TopHeadLines.Article> selectedArticle = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public LiveData<TopHeadLines.Article> getSelectedArticle() {
        return selectedArticle;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public MyWebViewClient getMyWebViewClient() {
        return new MyWebViewClient();
    }

    @Inject
    public DetailsViewModel() {
        disposable = new CompositeDisposable();
    }

    public void setIsLoading(boolean isLoading) {
        loading.setValue(isLoading);
    }

    public void setSelectedArticle(TopHeadLines.Article article) {
        selectedArticle.setValue(article);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            disposable.add(Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        loading.setValue(false);
                    }));
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            loading.setValue(false);
        }

    }
}
