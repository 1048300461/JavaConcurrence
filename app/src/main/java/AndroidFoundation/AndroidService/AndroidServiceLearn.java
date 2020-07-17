package AndroidFoundation.AndroidService;

public class AndroidServiceLearn {
    //Service种类
    //按运行地点分类：本地服务(Local Service)、远程服务(Remote Service)
    //按运行类型分类：前台服务、后台服务
    //按使用方式分类：
        //startService启动的服务：一个服务执行后台任务、
        //bindService启动的服务：进行通信、
        //同时使用startService、bindService启动的服务

    //Service生命周期：
    //OnCreate()：系统在service第一次创建时执行此方法，来执行只运行一次的初始化工作。如果
    //service已经运行，这个方法不会被调用。

    //onStartCommand()：每次客户端调用startService()方法启动该Service都会回调该方法（多次调用）。一
    //旦这个方法执行，service就启动并且在后台长期运行。通过调用stopSelf()或
    //stopService()来停止服务。

    //onBind()：当组件调用bindService()想要绑定到service时(比如想要执行进程间通讯)系统调用
    //此方法（一次调用，一旦绑定后，下次再调用bindService()不会回调该方法）。在
    //你的实现中，你必须提供一个返回一个IBinder来以使客户端能够使用它与service通
    //讯，你必须总是实现这个方法，但是如果你不允许绑定，那么你应返回null。

    //onUnbind()：当前组件调用unbindService()，想要解除与service的绑定时系统调用此方法（一次
    //调用，一旦解除绑定后，下次再调用unbindService()会抛出异常）。

    //onDestroy：系统在service不再被使用并要销毁时调用此方法（一次调用）。service应在此方法
    //中释放资源，比如线程，已注册的侦听器，接收器等等．这是service收到的最后一
    //个调用。

    //三种不同情况下Service的声明周期情况
    //1.startService / stopService
    //生命周期顺序：onCreate->onStartCommand->onDestroy
    //如果一个Service被某个Activity 调用 Context.startService方法启动，那么不管是否
    //有Activity使用bindService绑定或unbindService解除绑定到该Service，该Service都
    //在后台运行，直到被调用stopService，或自身的stopSelf方法。当然如果系统资源
    //不足，android系统也可能结束服务，还有一种方法可以关闭服务，在设置中，通过
    //应用->找到自己应用->停止。
    //注意点：
    //①第一次 startService 会触发 onCreate 和 onStartCommand，以后在服务运行过
    //程中，每次 startService 都只会触发 onStartCommand
    //②不论 startService 多少次，stopService 一次就会停止服务

    //2.bindService / unbindService
    //生命周期顺序：onCreate->onBind->onUnBind->onDestroy
    //如果一个Service在某个Activity中被调用bindService方法启动，不论bindService被
    //调用几次，Service的onCreate方法只会执行一次，同时onStartCommand方法始终
    //不会调用。
    //当建立连接后，Service会一直运行，除非调用unbindService来接触绑定、断开连
    //接或调用该Service的Context不存在了（如Activity被Finish——即通过bindService
    //启动的Service的生命周期依附于启动它的Context），系统在这时会自动停止该Service。

    //注意点：
    //第一次 bindService 会触发 onCreate 和 onBind，以后在服务运行过程中，每次
    //bindService 都不会触发任何回调

    //3.混合型（上面两种方式的交互）
    //当一个Service在被启动(startService)的同时又被绑定(bindService)，该Service将
    //会一直在后台运行，并且不管调用几次，onCreate方法始终只会调用一次，
    //onStartCommand的调用次数与startService调用的次数一致（使用bindService方法
    //不会调用onStartCommand）。同时，调用unBindService将不会停止Service，必
    //须调用stopService或Service自身的stopSelf来停止服务。

    //在什么情况下使用 startService 或 bindService 或 同时使用
    //startService 和 bindService？
    //①如果你只是想要启动一个后台服务长期进行某项任务那么使用 startService 便可
    //以了。
    //②如果你想要与正在运行的 Service 取得联系，那么有两种方法，一种是使用
    //broadcast ，另外是使用 bindService ，前者的缺点是如果交流较为频繁，容易造
    //成性能上的问题，并且 BroadcastReceiver 本身执行代码的时间是很短的（也许执
    //行到一半，后面的代码便不会执行），而后者则没有这些问题，因此我们肯定选择
    //使用 bindService（这个时候你便同时在使用 startService 和 bindService 了，这在
    //Activity 中更新 Service 的某些运行状态是相当有用的）。
    //③如果你的服务只是公开一个远程接口，供连接上的客服端（android 的 Service
    //是C/S架构）远程调用执行方法。这个时候你可以不让服务一开始就运行，而只用
    //bindService ，这样在第一次 bindService 的时候才会创建服务的实例运行它，这会
    //节约很多系统资源，特别是如果你的服务是Remote Service，那么该效果会越明显
    //（当然在 Service 创建的时候会花去一定时间，你应当注意到这点）。
}
