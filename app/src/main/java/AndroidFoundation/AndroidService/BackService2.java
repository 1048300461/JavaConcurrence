package AndroidFoundation.AndroidService;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BackService2 extends Service {
    private Thread mThread;
    private static final String TAG = "ZCC";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 返回MyBinder对象
        return new MyBinder();
    }

    class MyBinder extends Binder{
        public void showTip(){
            Log.d(TAG, "我是来此服务的提示");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //前台调用
    //通过以下方式绑定服务
    //bindService(mIntent, con, BIND_AUTO_CREATE);
    //con参数
    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BackService2.MyBinder myBinder = (BackService2.MyBinder) iBinder;
            myBinder.showTip();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
