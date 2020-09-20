package in.godofcode.task;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DBApi {
    @GET("movie/popular")
    Call<DBResponse> getMovies(@Query("api_key") String api_key);
}
