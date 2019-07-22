package com.example.sayaradz.Model
import com.example.sayaradz.Data.*
import retrofit2.Call
import retrofit2.http.*


interface RestService {


    @GET("automobilist/manufacturers")
    fun ListMarque(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<ManufactorList>

    @GET("automobilist/{manufacturer}/models")
    fun ListModel(
        @Path("manufacturer") manufacturer : Int,
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<ModelList>


    @GET("automobilist/{model}/versions")
    fun ListVersion(
        @Path("model") model : String,
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) : Call<VersionsList>


    @GET("automobilist/ads")
    fun ListAds(
        @Query("page") page : Int,
        @Query("page_size") page_size : Int
    ) :  Call<AdList>

   @POST("automobilist/followed-models")
   fun followModel(
       @Field("Model " )Model  : String,
       @Field("Automobilist") Automobilist: Int
   ): Call<FollowedModel>

    @POST("automobilist/followed-versions")
    fun followVersion(
        @Field("Version " )Version  : String,
        @Field("Automobilist") Automobilist: Int
    ): Call<FollowedVersion>

    @POST("automobilist/followed-versions/{id}")
    fun UnfollowVersion(
        @Path("ID") ID: Int
    ): Call<Void>

    @POST("automobilist/followed-models/{id}")
    fun UnfollowModel(
        @Path("ID") ID: Int
    ): Call<Void>


    @GET("automobilist/automobilist-followed-models")
    fun getFollowedModels(
        @Query ("automobilist") automobilist:Int
    ): Call<MutableList<FollowedModel>>


    @GET("automobilist/automobilist-followed-versions")
    fun getFolloweVersions(
        @Query ("automobilist") automobilist:Int
    ): Call<MutableList<FollowedVersion>>









}