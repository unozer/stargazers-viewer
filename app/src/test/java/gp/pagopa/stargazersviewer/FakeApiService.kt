package gp.pagopa.stargazersviewer

import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer
import gp.pagopa.stargazersviewer.data.remote.StargazersApiService
import kotlinx.coroutines.delay
import retrofit2.http.Path
import retrofit2.http.Query

class FakeApiService : StargazersApiService {

    companion object {
        val remoteStargazers = arrayListOf(
            RemoteStargazer("QuantDev-chaos", "https://avatars.githubusercontent.com/u/85345416?v=4"),
            RemoteStargazer("KaiDevrim", "https://avatars.githubusercontent.com/u/36937771?v=4"),
            RemoteStargazer("dbaelz", "https://avatars.githubusercontent.com/u/1376279?v=4"),
            RemoteStargazer("lbarqueira", "https://avatars.githubusercontent.com/u/65919567?v=4"),
            RemoteStargazer("kyzyto", "https://avatars.githubusercontent.com/u/35492869?v=4"),
            RemoteStargazer("justthetech", "https://avatars.githubusercontent.com/u/48089837?v=4"),
            RemoteStargazer("elbeg", "https://avatars.githubusercontent.com/u/14962401?v=4"),
            RemoteStargazer("androidmalin", "https://avatars.githubusercontent.com/u/5959435?v=4"),
            RemoteStargazer("Antares9009", "https://avatars.githubusercontent.com/u/57113626?v=4")
        )
    }

    override suspend fun getStargazers(
        @Path(value = "owner") owner: String,
        @Path(value = "repo") repo: String,
        @Query(value = "per_page") perPage: Int,
        @Query(value = "page") page: Int
    ): List<RemoteStargazer> {
        delay(1000)
        return remoteStargazers
    }
}