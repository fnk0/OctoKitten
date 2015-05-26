package com.gabilheri.octokitten.ui.repo.code;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.gabilheri.octokitten.data_models.RepoContent;
import com.gabilheri.octokitten.ui.repo.RepoComponent;
import com.gabilheri.octokitten.ui.repo.WebViewFragment;
import com.gabilheri.octokitten.utils.FileUtils;

import java.io.IOException;

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
 * @since 5/15/15.
 */
public class SourceCodeFragment extends WebViewFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list;
        try {
            list = getActivity().getAssets().list("styles");
            for(String s : list) {
                Log.d(SourceCodeFragment.class.getSimpleName(), s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        if(getArguments() != null) {

            String url = getArguments().getString("url");

            Observable<RepoContent> contentObservable =  service.getRepoContent(url);
            mCompositeSubscription.add(
                    contentObservable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<RepoContent>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(RepoContent repoContent) {
                                    String renderedString = FileUtils.getHtmlString(repoContent);
                                    if(renderedString != null) {
                                        if(renderedString.equals("IMAGE")) {
                                            webView.setVisibility(WebView.GONE);
                                            imageContent.setVisibility(ImageView.VISIBLE);
                                            byte[] decodeString = Base64.decode(repoContent.getBase64content(), Base64.DEFAULT);
                                            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                            imageContent.setImageBitmap(decodeByte);
                                        } else {
                                            loadWebViewContent(renderedString);
                                        }
                                    }
                                }
                            })
            );
            AppObservable.bindFragment(this, contentObservable);
        }
    }

    @Override
    protected void inject(RepoComponent component) {
        component.inject(this);
    }
}
