package com.gabilheri.octokitten.ui.repo.readme;

import android.os.Bundle;

import com.gabilheri.octokitten.app.PrefManager;
import com.gabilheri.octokitten.data.api.github.GithubService;
import com.gabilheri.octokitten.data_models.MarkdownPost;
import com.gabilheri.octokitten.data_models.RepoContent;
import com.gabilheri.octokitten.network.GithubClient;
import com.gabilheri.octokitten.network.TokenInterceptor;
import com.gabilheri.octokitten.ui.repo.BaseRepoActivity;
import com.gabilheri.octokitten.utils.Preferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/25/15.
 */
public class ReadmeActivity extends BaseRepoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = PrefManager.with(this).getString(Preferences.AUTH_TOKEN, null);
        final GithubService githubService = new GithubClient(true,
                token == null ? null : new TokenInterceptor(this),
                this).createGithubService();

        Observable<List<RepoContent>> contents = githubService.getRepoContents(extras.getString("url"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        AppObservable.bindActivity(this, contents);

        contents.subscribe(new Subscriber<List<RepoContent>>() {
            @Override
            public void onCompleted() {
                unsubscribe();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<RepoContent> repoContents) {
                boolean hasReadme = false;

                for(RepoContent r : repoContents) {

                    if(r.getName().toLowerCase().contains("readme")) {
                        githubService.getRepoContent(r.getUrl().replaceAll(GithubClient.API_URL + "/", ""))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<RepoContent>() {
                                    @Override
                                    public void onCompleted() {
                                        unsubscribe();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(RepoContent repoContent) {
                                        githubService.renderMarkdown(new MarkdownPost(repoContent.getContent()))
                                                .observeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Subscriber<Response>() {
                                                    @Override
                                                    public void onCompleted() {
                                                        unsubscribe();
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onNext(Response response) {

                                                        //Try to get response body
                                                        BufferedReader reader = null;
                                                        StringBuilder sb = new StringBuilder();
                                                        try {

                                                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                                                            String line;

                                                            try {
                                                                while ((line = reader.readLine()) != null) {
                                                                    sb.append(line).append("\n");
                                                                }
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }

                                                        ReadmeFragment readmeFragment = new ReadmeFragment();
                                                        Bundle b = new Bundle();
                                                        b.putString("readme", sb.toString());
                                                        readmeFragment.setArguments(b);
                                                        addFragmentToContainer(readmeFragment, null);
                                                    }
                                                });
                                    }
                                });
                        break;
                    }
                }
            }
        });


    }
}
