package com.greenwatermobile.backend;

import com.greenwatermobile.model.VariableResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface API {

    @GET("/devices/{device_id}/impulsy") VariableResponse readFlowStatus(
        @Path("device_id") String deviceId, @Query("access_token") String token)
        throws APIException;
}
