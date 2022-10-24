package gp.pagopa.stargazersviewer.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StargazersApiService {
    @GET("/repos/{owner}/{repo}/stargazers")
    suspend fun getStargazers(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): List<RemoteStargazer>
}