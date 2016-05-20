package wyjinbu.AppTool.http;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import wyjinbu.AppTool.entity.ResultModel;

public interface GankService {

    @GET("/random/data/{type}/{count}")
    void getRandomData(@Path("type") String type, @Path("count") String count, Callback<ResultModel> callback);
}
