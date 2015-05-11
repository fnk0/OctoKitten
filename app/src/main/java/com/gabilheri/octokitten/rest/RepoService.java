package com.gabilheri.octokitten.rest;

import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.data_models.RepoContent;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/9/15.
 */
public interface RepoService {

    @GET("/users/{user}/repos")
    Observable<List<Repo>> getRepos(
            @Path("user") String user
    );

    @GET("/repos/{user}/{name}/contents")
    Observable<List<RepoContent>> getRepoContents(
        @Path("user") String user,
        @Path("name") String name
    );

    @GET("/repos/{user}/{name}/contents/{path}")
    Observable<RepoContent> getRepoContent(
        @Path("user") String user,
        @Path("name") String name,
        @Path("path") String path
    );

    @GET("/repos/{user}/{name}")
    Observable<Repo> getRepo(
            @Path("user") String user,
            @Path("name") String name
    );



}
