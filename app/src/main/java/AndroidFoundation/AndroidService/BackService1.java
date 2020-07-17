package AndroidFoundation.AndroidService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

//不可交互的后台服务
public class BackService1 extends Service {
    private Thread mThread;

    private static String TAG = "ZCC";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //执行耗时操作

        mThread = new Thread(){
            @Override
            public void run() {
                try{
                    while (true){
                        //等待停止线程
                        if(this.isInterrupted()){
                            throw new InterruptedException();
                        }
                        Log.d(TAG, "run: 执行耗时操作");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //停止线程
        mThread.interrupt();
    }
}
