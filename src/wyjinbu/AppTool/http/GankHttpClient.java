package wyjinbu.AppTool.http;

import retrofit.Callback;
import retrofit.RestAdapter;
import wyjinbu.AppTool.entity.ResultModel;

public class GankHttpClient {

    private static final String API_URL = "http://gank.io/api";
    private static RestAdapter mRestAdapter;
    private static GankService mGankService;
    private static final String COUNT = "10";

    private static GankService getGankService() {
        if (mGankService == null) {
            if (mRestAdapter == null) {
                mRestAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
            }
            mGankService = mRestAdapter.create(GankService.class);
        }
        return mGankService;
    }


    public static void getRandomData(String type, Callback<ResultModel> callback) {
        getGankService().getRandomData(type, COUNT, callback);
    }
}
