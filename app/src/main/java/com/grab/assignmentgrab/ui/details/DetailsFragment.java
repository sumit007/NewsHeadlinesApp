package com.grab.assignmentgrab.ui.details;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
        detailsViewModel.getSelectedRepo().observe(this, repo -> {
            if (repo != null) {
                mWebView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.GhostWhite));
                mWebView.loadUrl(repo.getUrl());
                mWebView.getSettings().setLoadsImagesAutomatically(true);
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.setWebViewClient(new MyWebViewClient());
            }
        });
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (mProgressBar != null && mProgressBar.getVisibility() == View.GONE) {

                mProgressBar.setVisibility(View.VISIBLE);
                long delayInMillis = 2000;

                new Handler().postDelayed(() -> {
                    if (mProgressBar != null) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, delayInMillis);
            }
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }
        }

    }


}
