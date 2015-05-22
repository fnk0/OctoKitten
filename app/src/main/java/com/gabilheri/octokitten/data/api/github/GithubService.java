package com.gabilheri.octokitten.data.api.github;

import com.gabilheri.octokitten.data_models.LoginRequest;
import com.gabilheri.octokitten.data_models.Repo;
import com.gabilheri.octokitten.data_models.RepoContent;
import com.gabilheri.octokitten.data_models.UserToken;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/9/15.
 */
public interface GithubService {

    @GET("/users/{user}/repos")
    Observable<List<Repo>> getRepos(
            @Path("user") String user,
            @Query("sort") String sort
    );

    /**
     * List repos for the current authenticated user
     * @return
     *      Repos for a specific user
     */
    @GET("/user/repos")
    Observable<List<Repo>> getRepos(
            @Query("sort") String sort
    );

    @GET("/repos/{user}/{name}/contents/{path}")
    Observable<List<RepoContent>> getRepoContents(
        @Path("user") String user,
        @Path("name") String name,
        @Path("path") String path
    );

    @GET("/{url}")
    Observable<List<RepoContent>> getRepoContents(
            @Path(value = "url", encode = false) String url

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

    @DELETE("/authorizations/{id}")
    Observable<Object> signOut(
            @Path("id") String id
    );

    @POST("/authorizations")
    Observable<UserToken> signIn(
            @Body LoginRequest body
    );

//    @GET("/users/{owner}/received_events")
//    ArrayList<Feed> getFeed(
//            @Path("owner") String owner
//    );


}
