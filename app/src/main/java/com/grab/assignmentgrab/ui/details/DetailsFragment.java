package com.grab.assignmentgrab.ui.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.grab.assignmentgrab.R;
import com.grab.assignmentgrab.base.BaseFragment;
import com.grab.assignmentgrab.util.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.headline_details_wv)
    WebView mWebView;
    @BindView(R.id.progress_circular)
    ProgressBar mProgressBar;

    @Inject
    ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        displayRepo();

    }

    private void displayRepo() {
        detailsViewModel.getSelectedArticle().observe(this, repo -> {
            if (repo != null) {
                mWebView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.GhostWhite));
                mWebView.loadUrl(repo.getUrl());
                mWebView.getSettings().setLoadsImagesAutomatically(true);
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.setWebViewClient(detailsViewModel.getMyWebViewClient());
            }
        });

        detailsViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null && mProgressBar != null) {
                if (isLoading) {
                    mProgressBar.setVisibility(View.VISIBLE);

                } else {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }


}
