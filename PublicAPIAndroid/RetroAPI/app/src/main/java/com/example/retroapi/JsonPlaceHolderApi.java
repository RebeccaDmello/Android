package com.example.retroapi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface JsonPlaceHolderApi {

    //posts bcz in the url - https://jsonplaceholder.typicode.com/posts

    @GET("posts")
    Call<List<Post>>  getPosts();

}
