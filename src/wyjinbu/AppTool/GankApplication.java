package wyjinbu.AppTool;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class GankApplication extends Application {
	public static RequestQueue queue;
	
	
	public static RequestQueue getRequestQueue(){
		return queue;
	}
	
	
    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }
}
