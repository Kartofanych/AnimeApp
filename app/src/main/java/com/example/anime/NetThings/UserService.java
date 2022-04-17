package com.example.anime.NetThings;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("api/all.php")
    Call<String> allInfo();


}
