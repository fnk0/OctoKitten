package com.gabilheri.octokitten.ui.cards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.gabilheri.octokitten.data_models.RepoContent;
import com.gabilheri.octokitten.network.GithubClient;
import com.gabilheri.octokitten.network.TokenInterceptor;
import com.gabilheri.octokitten.ui.repo.ReposListActivity;
import com.gabilheri.octokitten.ui.repo.code.CodeActivity;
import com.gabilheri.octokitten.utils.FileType;
import com.gabilheri.octokitten.utils.FileUtils;
import com.gabilheri.octokitten.utils.Preferences;

import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/26/14.
 */
public class CardFileItem extends Card implements Card.OnCardClickListener {

    private static final String LOG_TAG = CardFileItem.class.getSimpleName();
    private RepoContent repoContent;

    public CardFileItem(Context context, RepoContent repoContent) {
        super(context, R.layout.list_item_file);
        this.repoContent = repoContent;
        this.setOnClickListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        TextView titleView = (TextView) view.findViewById(R.id.list_title);
        TextView descriptionview = (TextView) view.findViewById(R.id.list_message);
        ImageView fileIcon = (ImageView) view.findViewById(R.id.list_icon);

        if(repoContent != null) {
            titleView.setText(repoContent.getName());
            descriptionview.setText("");

            if(repoContent.getType().equals(getContext().getString(R.string.dir))) {
                fileIcon.setImageResource(FileUtils.getFileDrawable(FileType.DIR));
            } else {
                fileIcon.setImageResource(FileUtils.getFileDrawable(FileUtils.getFileType(repoContent.getName())));
            }
        }
    }

    @Override
    public void onClick(Card card, View view) {
        Log.i(LOG_TAG, repoContent.getName());
        Bundle b = new Bundle();
        String url = repoContent.getUrl().replaceAll(GithubClient.API_URL + "/", "");
        b.putString(getContext().getString(R.string.url), url);
        b.putString(getContext().getString(R.string.title), repoContent.getPath());
        if(repoContent.getType().equals(getContext().getString(R.string.dir))) {
            String token = PrefManager.with(getContext()).getString(Preferences.AUTH_TOKEN, null);
            GithubService githubService = new GithubClient(true, token == null ? null : new TokenInterceptor(getContext()), getContext()).createGithubService();
            githubService.getRepoContents(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<RepoContent>>() {
                        boolean sent = false;
                        @Override
                        public void onCompleted() {
                            unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<RepoContent> contents) {
                            if(!sent) {
                                sent = true;
                                if(getContext() instanceof ReposListActivity) {
                                    ((ReposListActivity) getContext()).addDir(contents, repoContent.getPath());
                                }
                            }

                        }
                    });
        } else {
            Intent i = new Intent(getContext(), CodeActivity.class);
            i.putExtra(Intent.EXTRA_INTENT, b);
            getContext().startActivity(i);
        }
    }
}
