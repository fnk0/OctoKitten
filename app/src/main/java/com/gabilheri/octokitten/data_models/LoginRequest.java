package com.gabilheri.octokitten.data_models;

import com.gabilheri.octokitten.network.GithubClient;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/13/15.
 */
public class LoginRequest {

    final String client_id = GithubClient.CLIENT_ID;
    final String client_secret = GithubClient.CLIENT_SECRET;
    final String[] scopes = {"user","repo","gist","notifications","repo:status"};
    final String note = "OctoKitten App";

    public LoginRequest() {

    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String[] getScopes() {
        return scopes;
    }

    public String getNote() {
        return note;
    }
}
