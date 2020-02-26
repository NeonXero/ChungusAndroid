package net.neonlotus.chungusadmin.networking;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface RestClient {
//    @POST("api/caregiver/login")
//    Call<ResponseBody> login(@Body LoginBody request);

//    @GET("api/caregiver/clients")
//    Call<ResponseBody> getClients(@Query("CustomerID") int custID,
//                                  @Query("Latitude") double lat,
//                                  @Query("Longitude") double lng);

    @Streaming
    @GET("api?req=weeklyStatsGrab&guildId=FcIj2voa5B&dl=5217332654608149P&guildName=ChungusCrew")
    Call<ResponseBody> getWeeklyStats();

    @GET("todos/1")
    Call<ResponseBody> test();

    @GET("update.pl?guildId=FcIj2voa5B")
    Call<ResponseBody> updateGuild();

}

/*
ADDING HEADERS
@Headers({"Cache-Control: max-age=640000", "User-Agent: My-App-Name"})
@GET("/some/endpoint")


HEADER TO ENDPOINT
@Multipart
@POST("/some/endpoint")
Call<SomeResponse> someEndpoint(@Header("Cache-Control") int maxAge)


FORM DATA
@FormUrlEncoded
@POST("/some/endpoint")
Observable<SomeResponse> someEndpoint(@Field("code") String code);


MULTIPART
@Multipart
@POST("some/endpoint")
Call<Response> uploadImage(@Part("description") String description, @Part("image") RequestBody image)


FILE
MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
file = new File("/storage/emulated/0/Pictures/MyApp/test.png");
RequestBody requestBody = RequestBody.create(MEDIA_TYPE_PNG, file);

Call<Response> call = apiService.uploadImage("test", requestBody);


UNIQUE FILENAME
RequestBody requestBody = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("title", "Square Logo")
        .addFormDataPart("image", "logo-square.png",
            RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
        .build();


FORM URL ENCODING
@FormUrlEncoded
@POST("some/endpoint")
Call<SomeResponse> someEndpoint(@FieldMap Map<String, String> names);



POSTING JSON
public class User {

  @SerializedName("id")
  int mId;

  @SerializedName("name")
  String mName;

  public User(int id, String name ) {
    this.mId = id;
    this.mName = name;
  }
}
 */