package wyjinbu.AppTool.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast toast;
	
	public static void showToast(Context context,String string){
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		}else {
			toast.setText(string);
		}
		toast.show();
	}
	
	public static void cancelToast(){
		if (toast!=null) {
		toast.cancel();
		}
	}
	
	
}
