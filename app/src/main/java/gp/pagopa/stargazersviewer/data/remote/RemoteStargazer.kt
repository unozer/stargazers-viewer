package gp.pagopa.stargazersviewer.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteStargazer(
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String
)