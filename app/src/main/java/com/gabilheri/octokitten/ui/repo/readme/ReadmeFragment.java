package com.gabilheri.octokitten.ui.repo.readme;

import android.os.Bundle;
import android.view.View;

import com.gabilheri.octokitten.ui.repo.WebViewFragment;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class ReadmeFragment extends WebViewFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle b = getArguments();
        String readme = b.getString("readme");

        loadWebViewContent(String.format(getHtml(), readme));
    }

    public String getHtml() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"markdown/solarized_light.css\">\n" +
                "<link rel=\"stylesheet\" href=\"styles/androidstudio.css\">\n" +
                "</head>\n" +
                "<body>" +
                "%s" +
                "<script src=\"highlight.js\"></script>" +
                "<script>hljs.initHighlightingOnLoad();</script>" +
                "</body>\n" +
                "</html>";
    }
}
