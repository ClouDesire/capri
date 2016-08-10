package com.cloudesire.capra;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

public class CapraClient
{
    private final Retrofit retrofit;

    public CapraClient( String baseUrl )
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

    public CapraService getService()
    {
        return retrofit.create( CapraService.class );
    }
}
