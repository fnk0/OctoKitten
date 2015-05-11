package com.gabilheri.octokitten.data_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/7/15.
 */
public class UserPlan {

    private long collaborators;

    @SerializedName("private_repos")
    private long privateRepos;

    private long space;
    private String name;

    public UserPlan() {
    }

    public long getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(long collaborators) {
        this.collaborators = collaborators;
    }

    public long getPrivateRepos() {
        return privateRepos;
    }

    public void setPrivateRepos(long privateRepos) {
        this.privateRepos = privateRepos;
    }

    public long getSpace() {
        return space;
    }

    public void setSpace(long space) {
        this.space = space;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
