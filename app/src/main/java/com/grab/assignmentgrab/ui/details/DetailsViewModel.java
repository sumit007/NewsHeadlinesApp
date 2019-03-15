package com.grab.assignmentgrab.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.grab.assignmentgrab.data.api.NetworkRepository;
import com.grab.assignmentgrab.data.model.TopHeadLines;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailsViewModel extends ViewModel {

    private final NetworkRepository repoRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<TopHeadLines.Article> selectedRepo = new MutableLiveData<>();

    public LiveData<TopHeadLines.Article> getSelectedRepo() {
        return selectedRepo;
    }

    @Inject
    public DetailsViewModel(NetworkRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
    }

    /*public void setSelectedRepo( repo) {
        selectedRepo.setValue(repo);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedRepo.getValue() != null) {
            outState.putStringArray("repo_details", new String[] {
                    selectedRepo.getValue().owner.login,
                    selectedRepo.getValue().name
            });
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedRepo.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details"));
            }
        }
    }

    private void loadRepo(String[] repo_details) {
        disposable.add(repoRepository.getRepo(repo_details[0], repo_details[1]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Repo>() {
            @Override
            public void onSuccess(Repo value) {
                selectedRepo.setValue(value);
            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }*/

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
