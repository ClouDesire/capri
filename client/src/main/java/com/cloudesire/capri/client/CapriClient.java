package com.cloudesire.capri.client;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

public class CapriClient
{
    private final Retrofit retrofit;

    public CapriClient( String baseUrl )
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout( 30, TimeUnit.SECONDS ) // connect timeout
                .readTimeout( 60, TimeUnit.SECONDS )    // socket timeout
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl( baseUrl )
                .client( client )
                .build();
    }

    public CapriService getService()
    {
        return retrofit.create( CapriService.class );
    }
}
