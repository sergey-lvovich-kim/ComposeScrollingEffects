package org.mikyegresl.composescrollanimations.network

import retrofit2.http.GET

interface ValorantApi {

    @GET("v1/agents")
    suspend fun getAgents(): GetAgentsResponse
}