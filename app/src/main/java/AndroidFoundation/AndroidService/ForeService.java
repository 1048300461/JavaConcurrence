package AndroidFoundation.AndroidService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.WorkKnowledge.R;

//前台服务创建很简单，其实就在Service的基础上创建一个Notification，然后使用
//Service的startForeground()方法即可启动为前台服务。
public class ForeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        beginForeService();
    }

    private void beginForeService() {
        //创建通知
        Notification.Builder mBuilder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("2020-7-17")
                .setContentText("您有一条未读短息...");
        //创建点跳转的Intent(这个跳转是跳转到通知详情页)
        Intent intent = new Intent(this, NotificationShow.class);
        //创建通知详情页的栈
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //为其添加父栈 当从通知详情页回退时，将退到添加的父栈中
        stackBuilder.addParentStack(NotificationShow.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        //设置跳转Intent到通知中
        mBuilder.setContentIntent(pendingIntent);
        //获取通知服务
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //构建通知
        Notification notification = mBuilder.build();
        //显示通知
        nm.notify(0, notification);
        //启动前台服务
        startForeground(0, notification);

        //启动前台服务
        startService(new Intent(this, ForeService.class));
    }

}
