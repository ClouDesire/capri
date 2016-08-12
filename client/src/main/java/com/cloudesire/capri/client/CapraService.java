package com.cloudesire.capri.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CapraService
{
    @GET( "/cap/{cap}" )
    Call<ProvinceData> query( @Path( "cap" ) String cap);
}
