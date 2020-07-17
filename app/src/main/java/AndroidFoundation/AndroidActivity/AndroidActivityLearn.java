package AndroidFoundation.AndroidActivity;

public class AndroidActivityLearn {
    //onCreate：当 Activity 第一次创建时会被调用。这是生命周期的第一个方法。
    //在这个方法中，可以做一些初始化工作，比如调用setContentView去加载界面布局
    //资源，初始化Activity所需的数据。当然也可借助onCreate()方法中的Bundle对象来
    //回复异常情况下Activity结束时的状态（后面会介绍）

    //onStart：表示Activity正在被启动，即将开始，这时Activity已经出现了，但是还
    //没有出现在前台，无法与用户交互。这个时候可以理解为Activity已经显示出来，
    //但是我们还看不到。
    //onResume：表示Activity已经可见了，并且出现在前台并开始活动。需要和
    //onStart()对比，onStart的时候Activity还在后台，onResume的时候Activity才显示到前台。
    //onPause：表示 Activity正在停止，仍可见，正常情况下，紧接着onStop就会被
    //调用。在特殊情况下，如果这个时候快速地回到当前Activity，那么onResume就会
    //被调用（极端情况）。onPause中不能进行耗时操作，会影响到新Activity的显
    //示。因为onPause必须执行完，新的Activity的onResume才会执行。
    //onStop：表示Activity即将停止，不可见，位于后台。可以做稍微重量级的回收
    //工作，同样不能太耗时。
    //onDestroy：表示Activity即将销毁，这是Activity生命周期的最后一个回调，可以
    //做一些回收工作和最终的资源回收。
    //onRestart：表示Activity正在重新启动。一般情况下，当当前Activity从不可见
    //重新变为可见状态时，onRestart就会被调用。这种情形一般是用户行为导致的，比
    //如用户按Home键切换到桌面或打开了另一个新的Activity，接着用户又回到了这个
    //Activity。

    //生命周期的几种普通情况：
    //①针对一个特定的Activity，第一次启动，回调如下：onCreate()->onStart()-
    //>onResume()
    //②用户打开新的Activiy的时候，上述Activity的回调如下：onPause()->onStop()
    //③再次回到原Activity时，回调如下：onRestart()->onStart()->onResume()
    //④按back键回退时，回调如下：onPause()->onStop()->onDestory()
    //⑤按Home键切换到桌面后又回到该Actitivy，回调如下：onPause()->onStop()-
    //>onRestart()->onStart()->onResume()
    //⑥调用finish()方法后，回调如下：onDestory()(以在onCreate()方法中调用为例，
    //不同方法中回调不同，通常都是在onCreate()方法中调用)

    //在横竖屏切换的过程，会发生Activity被销毁并重建的过程
    //期间会回调两个方法：onSaveInstanceState和onRestoreInstanceState
    //在Activity由于异常情况下终止时，系统会调用onSaveInstanceState来保存当前
    //Activity的状态。这个方法的调用是在onStop之前，它和onPause没有既定的时序关
    //系，该方法只在Activity被异常终止的情况下调用。当异常终止的Activity被重建以
    //后，系统会调用onRestoreInstanceState，并且把Activity销毁时
    //onSaveInstanceState方法所保存的Bundle对象参数同时传递给
    //onRestoreInstanceState和onCreate方法。因此，可以通过
    //onRestoreInstanceState方法来恢复Activity的状态，该方法的调用时机是在onStart
    //之后。其中onCreate和onRestoreInstanceState方法来恢复Activity的状态的区
    //别： onRestoreInstanceState回调则表明其中Bundle对象非空，不用加非空判断。
    //onCreate需要非空判断。建议使用onRestoreInstanceState。
    //横竖屏切换的生命周期：onPause()->onSaveInstanceState()-> onStop()-
    //>onDestroy()->onCreate()->onStart()->onRestoreInstanceState->onResume()
    //可以通过在AndroidManifest文件的Activity中指定如下属性：
    //android:configChanges = "orientation| screenSize"
    //来避免横竖屏切换时，Activity的销毁和重建，而是回调了下面的方法：
    //@Override
    //public void onConfigurationChanged(Configuration newConfig)
    //{
    //  super.onConfigurationChanged(newConfig);
    //}

    //资源内存不足导致优先级低的Activity被杀死
    //Activity优先级的划分和下面的Activity的三种运行状态是对应的。
    //(1) 前台Activity——正在和用户交互的Activity，优先级最高。
    //(2) 可见但非前台Activity——比如Activity中弹出了一个对话框，导致Activity可见但
    //是位于后台无法和用户交互。
    //(3) 后台Activity——已经被暂停的Activity，比如执行了onStop，优先级最低。

    //Activity的三种运行状态：Resumed活动状态，Paused暂停状态，Stopped停止状态

    //Activity的四种启动方式：
    //标准模式（standard）
        //每启动一次Activity，就会创建一个新的Activity实例并置于栈顶。谁启动了这个
        //Activity，那么这个Activity就运行在启动它的那个Activity所在的栈中。

        //特殊情况，如果在Service或Application中启动一个Activity，其并没有所谓的任
        //务栈，可以使用标记位Flag来解决。解决办法：为待启动的Activity指定
        //FLAG_ACTIVITY_NEW_TASK标记位，创建一个新栈。

        //应用场景： 绝大多数Activity。如果以这种方式启动的Activity被跨进程调用，在5.0
        //之前新启动的Activity实例会放入发送Intent的Task的栈的顶部，尽管它们属于不同
        //的程序，这似乎有点费解看起来也不是那么合理，所以在5.0之后，上述情景会创建
        //一个新的Task，新启动的Activity就会放入刚创建的Task中，这样就合理的多了。

    //栈顶复用模式（singleTop）
        //如果需要新建的Activity位于任务栈栈顶，那么此Activity的实例就不会重建，而是重
        //用栈顶的实例。并回调onNewIntent方法
        //由于不会重建一个Activity实例，则不会回调其他生命周期方法。
        //如果栈顶不是新建的Activity,就会创建该Activity新的实例，并放入栈顶。

        //应用场景： 在通知栏点击收到的通知，然后需要启动一个Activity，这个Activity就
        //可以用singleTop，否则每次点击都会新建一个Activity。当然实际的开发过程中，测
        //试妹纸没准给你提过这样的bug：某个场景下连续快速点击，启动了两个Activity。
        //如果这个时候待启动的Activity使用 singleTop模式也是可以避免这个Bug的。同
        //standard模式，如果是外部程序启动singleTop的Activity，在Android 5.0之前新创建
        //的Activity会位于调用者的Task中，5.0及以后会放入新的Task中。

    //栈内复用模式（singleTask）
        //该模式是一种单例模式，即一个栈内只有一个该Activity实例。该模式，可以通过在
        //AndroidManifest文件的Activity中指定该Activity需要加载到那个栈中，即singleTask
        //的Activity可以指定想要加载的目标栈。singleTask和taskAffinity配合使用，指定开
        //启的Activity加入到哪个栈中。

        //关于taskAffinity的值： 每个Activity都有taskAffinity属性，这个属性指出了它希望
        //进入的Task。如果一个Activity没有显式的指明该Activity的taskAffinity，那么它的这
        //个属性就等于Application指明的taskAffinity，如果Application也没有指明，那么该
        //taskAffinity的值就等于包名。

        //在这种模式下，如果Activity指定的栈不存在，则创建一个栈，并把创建的Activity压
        //入栈内。如果Activity指定的栈存在，如果其中没有该Activity实例，则会创建
        //Activity并压入栈顶，如果其中有该Activity实例，则把该Activity实例之上的Activity
        //杀死清除出栈，重用并让该Activity实例处在栈顶，然后调用onNewIntent()方法。

        //应用场景： 大多数App的主页。对于大部分应用，当我们在主界面点击回退按钮的
        //时候都是退出应用，那么当我们第一次进入主界面之后，主界面位于栈底，以后不
        //管我们打开了多少个Activity，只要我们再次回到主界面，都应该使用将主界面
        //Activity上所有的Activity移除的方式来让主界面Activity处于栈顶，而不是往栈顶新
        //加一个主界面Activity的实例，通过这种方式能够保证退出应用时所有的Activity都能
        //报销毁。

    //单例模式（singleInstance）
        //作为栈内复用模式（singleTask）的加强版,打开该Activity时，直接创建一个新的任
        //务栈，并创建该Activity实例放入新栈中。一旦该模式的Activity实例已经存在于某个
        //栈中，任何应用再激活该Activity时都会重用该栈中的实例。

    //Activity的Flags。以下为常用的，用于设定Activity的启动模式
    //(1)FLAG_ACTIVITY_NEW_TASK 其效果与指定Activity为singleTask模式一致。
    //(2)FLAG_ACTIVITY_SINGLE_TOP 其效果与指定Activity为singleTop模式一致。
    //(3)FLAG_ACTIVITY_CLEAR_TOP 具有此标记位的Activity，当它启动时，在同一
    //个任务栈中所有位于它上面的Activity都要出栈。如果和singleTask模式一起出现，
    //若被启动的Activity已经存在栈中，则清除其之上的Activity，并调用该Activity的
    //onNewIntent方法。如果被启动的Activity采用standard模式，那么该Activity连同之
    //上的所有Activity出栈，然后创建新的Activity实例并压入栈中。
}
