package com.gabilheri.octokitten.ui.repo;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.ui.DefaultFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public class WebViewFragment extends DefaultFragment<RepoComponent> {

    @InjectView(R.id.webview)
    WebView webView;

    @InjectView(R.id.image)
    ImageView imageContent;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    public void loadWebViewContent(String htmlString) {
        if(htmlString != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setUseWideViewPort(true);
            //Log.i("HTML STRING: ", renderedString);
            webView.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "utf-8", null);
        } else {
            webView.loadDataWithBaseURL("file:///android_asset/", "<h1>404 not found! </h1>", "text/html", "utf-8", null);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void inject(RepoComponent component) {
        component.inject(this);
    }
}
