package com.gabilheri.octokitten.data_models;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */

import com.google.gson.annotations.Expose;

public class Links {

    @Expose
    private String self;
    @Expose
    private String git;
    @Expose
    private String html;

    /**
     *
     * @return
     * The self
     */
    public String getSelf() {
        return self;
    }

    /**
     *
     * @param self
     * The self
     */
    public void setSelf(String self) {
        this.self = self;
    }

    /**
     *
     * @return
     * The git
     */
    public String getGit() {
        return git;
    }

    /**
     *
     * @param git
     * The git
     */
    public void setGit(String git) {
        this.git = git;
    }

    /**
     *
     * @return
     * The html
     */
    public String getHtml() {
        return html;
    }

    /**
     *
     * @param html
     * The html
     */
    public void setHtml(String html) {
        this.html = html;
    }

}