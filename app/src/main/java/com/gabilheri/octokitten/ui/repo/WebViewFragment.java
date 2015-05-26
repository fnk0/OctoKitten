package com.gabilheri.octokitten.ui.repo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    protected WebView webView;

    @InjectView(R.id.image)
    protected ImageView imageContent;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    public void loadWebViewContent(String htmlString) {
        if(htmlString != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setCacheMode(WebView.PERSISTENT_ALL_CACHES);
            webView.setWebViewClient(new WebViewCustomClient());
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.clearCache(true);
            webView.clearFormData();
            webView.clearMatches();
            webView.clearSslPreferences();
            //Log.i("HTML STRING: ", renderedString);
//            logger.i(htmlString);
            webView.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "utf-8", null);
        } else {
            webView.loadDataWithBaseURL("file:///android_asset/", "<h1>404 not found! </h1>", "text/html", "utf-8", null);
        }
    }


    private class WebViewCustomClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
            return true;
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
