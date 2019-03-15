package com.grab.assignmentgrab.ui.list;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grab.assignmentgrab.R;
import com.grab.assignmentgrab.data.model.TopHeadLines;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopHeadlineListAdapter extends RecyclerView.Adapter<TopHeadlineListAdapter.HeadLineViewHolder>{

    private ArticleSelectedListener articleSelectedListener;
    private final List<TopHeadLines.Article> data = new ArrayList<>();

    TopHeadlineListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, ArticleSelectedListener articleSelectedListener) {
        this.articleSelectedListener = articleSelectedListener;
        viewModel.getArticles().observe(lifecycleOwner, articles -> {
            data.clear();
            if (articles != null) {
                data.addAll(articles);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public HeadLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_line_item, parent, false);
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

        @BindView(R.id.headline_author_by_tv)
        TextView authorNameTv;
        @BindView(R.id.headline_description_tv)
        TextView headLineDescriptionTextView;
        @BindView(R.id.headline_content_tv)
        TextView contentTextView;
        @BindView(R.id.headline_title_tv)
        TextView headLineTitleTextView;
        @BindView(R.id.headline_published_at_tv)
        TextView headLinePublishedAtTextView;
        @BindView(R.id.headline_iv)
        ImageView headLineImageView;

        private TopHeadLines.Article article;

        HeadLineViewHolder(View itemView, ArticleSelectedListener articleSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(article != null) {
                    articleSelectedListener.onArticleSelected(article);
                }
            });
        }

        void bind(TopHeadLines.Article article) {
            this.article = article;
            authorNameTv.setText(article.getAuthor());
            headLineDescriptionTextView.setText(article.getDescription());
            contentTextView.setText(String.valueOf(article.getContent()));
            headLineTitleTextView.setText(article.getTitle());
            headLinePublishedAtTextView.setText(article.getPublishedAt());
            Picasso.get().load(article.getUrlToImage()).into(headLineImageView);
        }
    }
}
