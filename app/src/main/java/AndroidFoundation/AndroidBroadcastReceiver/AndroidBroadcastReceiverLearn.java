package AndroidFoundation.AndroidBroadcastReceiver;


import android.content.IntentFilter;

public class AndroidBroadcastReceiverLearn {
    //BroadcastReceiver的作用
    //用于监听 / 接收 应用发出的广播消息，并做出响应
    //应用场景 a. 不同组件之间通信（包括应用内 / 不同应用之间）
    //b. 与 Android 系统在特定情况下的通信
    //如当电话呼入时、网络可用时
    //c. 多线程通信

    //广播接收器注册
    //静态注册：在AndroidManifest.xml里通过 标签声明
    //动态注册：最好在Activity的onResume()注册、onPause()注销，不允许重复注册和重复注销
        //MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        //IntentFilter mIntentFilter = new IntentFilter();
        //设置接收广播的类型
        //mIntentFilter.addAction(android.net.conn.CONNECTIVITY_CHANGE);
        //registerReceiver(myBroadcastReceiver, mIntentFilter);

    //广播的发送使用Intent标识通过sendBroadcast()方法发送出去
    //广播的类型：
    //普通广播（Normal Broadcast）：即开发者自身定义intent的广播（最常用）
    //系统广播（System Broadcast）：当使用系统广播时，只需要在注册广播接收者时定义相关的action即可，并不
    //需要手动发送广播，当系统有相关操作时会自动进行系统广播
    //有序广播（Ordered Broadcast）：有序是针对广播接收者而言的，广播接受者接收广播的顺序规则（同时面向静态和动态注册的广播接受者）
    //1. 按照Priority属性值从大-小排序；
    //2. Priority属性相同者，动态注册的广播优先；
    //特点
    //1. 接收广播按顺序接收
    //2. 先接收的广播接收者可以对广播进行截断，即后接收的广播接收者不再接
    //收到此广播；
    //3. 先接收的广播接收者可以对广播进行修改，那么后接收的广播接收者将接
    //收到被修改后的广播
    //粘性广播（Sticky Broadcast）：已失效

    //App应用内广播（Local Broadcast）：使用：
    //1. 注册广播时将exported属性设置为false，使得非本App内部发出的此广播
    //不被接收；
    //2. 在广播发送和接收时，增设相应权限permission，用于权限验证；
    //3. 发送广播时指定该广播接收器所在的包名，此广播将只会发送到此包中的
    //App内与之相匹配的有效广播接收器中。
    //通过 intent.setPackage(packageName) 指定包名
    //对于LocalBroadcastManager方式发送的应用内广播，只能通过
    //LocalBroadcastManager动态注册，不能静态注册
}
