package com.grab.assignmentgrab.ui.list;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grab.assignmentgrab.R;
import com.grab.assignmentgrab.data.model.TopHeadLines;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopHeadlineListAdapter extends RecyclerView.Adapter<TopHeadlineListAdapter.HeadLineViewHolder>{

    private ArticleSelectedListener articleSelectedListener;
    private final List<TopHeadLines.Article> data = new ArrayList<>();

    TopHeadlineListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, ArticleSelectedListener articleSelectedListener) {
        this.articleSelectedListener = articleSelectedListener;
        viewModel.getArticles().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public HeadLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new HeadLineViewHolder(view, articleSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadLineViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class HeadLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name)
        TextView repoNameTextView;
        @BindView(R.id.tv_repo_description)
        TextView repoDescriptionTextView;
        @BindView(R.id.tv_forks)
        TextView forksTextView;
        @BindView(R.id.tv_stars)
        TextView starsTextView;

        private TopHeadLines.Article repo;

        HeadLineViewHolder(View itemView, ArticleSelectedListener articleSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(repo != null) {
                    articleSelectedListener.onArticleSelected(repo);
                }
            });
        }

        void bind(TopHeadLines.Article repo) {
            this.repo = repo;
            repoNameTextView.setText(repo.getTitle());
            repoDescriptionTextView.setText(repo.getDescription());
            forksTextView.setText(String.valueOf(repo.getSource()));
            starsTextView.setText(String.valueOf(repo.getPublishedAt()));
        }
    }
}
