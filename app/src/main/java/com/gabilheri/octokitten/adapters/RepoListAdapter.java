package com.gabilheri.octokitten.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.utils.CustomUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/22/15.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private List<Repo> repos;
    private Typeface typeface;

    public RepoListAdapter() {
        repos = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_card, parent, false);
        typeface = CustomUtils.getGithubTypeface(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo r = repos.get(position);
        holder.repoIcon.setTypeface(typeface);

        if(r != null) {
            holder.titleView.setText(r.getName());
            holder.subTitleView.setText(r.getDescription());
            holder.starCountView.setText("" + r.getStargazersCount());

            if(r.getFork()) {
                holder.repoIcon.setText("\uf002");
            } else {
                holder.repoIcon.setText("\uf001");
            }
        }
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.repo_title)
        TextView titleView;

        @InjectView(R.id.repo_subtitle)
        TextView subTitleView;

        @InjectView(R.id.stars_count)
        TextView starCountView;

        @InjectView(R.id.repo_icon)
        TextView repoIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
