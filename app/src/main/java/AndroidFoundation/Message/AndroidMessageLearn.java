package AndroidFoundation.Message;

import android.os.Looper;

public class AndroidMessageLearn {
    // 消息机制的模型：
    //消息机制主要包括：MessageQueue，Handler和Looper这三大部分和Message
    //Message：需要传递的消息，可以传递数据
    //MessageQueue：消息队列，但是它的内部实现并不是用的队列，实际上是通过一
    //个单链表的数据结构来维护消息列表，因为单链表在插入和删除上比较有优势。主
    //要功能向消息池投递消息(MessageQueue.enqueueMessage)和取走消息池的消息
    //(MessageQueue.next)；
    //Handler：消息辅助类，主要功能向消息池发送各种消息事件(Handler.sendMessage)
    //和处理相应消息事件(Handler.handleMessage);
    //Looper：不断循环执行(Looper.loop)，从MessageQueue中读取消息，按分发机制将消息分发给目标处理者

    //消息机制的架构：消息机制的运行流程：在子线程执行完耗时操作，当Handler发送消息时，将会调
    //用 MessageQueue.enqueueMessage ，向消息队列中添加消息。当通
    //过 Looper.loop 开启循环后，会不断地从线程池中读取消息，即调
    //用 MessageQueue.next ，然后调用目标Handler（即发送该消息的Handler）
    //的 dispatchMessage 方法传递消息，然后返回到Handler所在线程，目标Handler
    //收到消息，调用 handleMessage 方法，接收消息，处理消息。

    //Looper的初始化，默认调用prepare(true)，表示这个Looper可以退出，若为false，表示当前Looper不可以退出
    //发送消息有几种方式，但是归根结底都是调用了sendMessageAtTime()方法，在子线程中通过Handler的post()方式
    //或send()方式发送消息，最终都是调用了sendMessageAtTime()方法。
    //就连子线程中调用Activity中的runOnUiThread()中更新UI，其实也是发送消息通知
    //主线程更新UI，最终也会调用 sendMessageAtTime() 方法。

    //消息分发的优先级：
    //Message的回调方法： message.callback.run() ，优先级最高；
    //Handler中Callback的回调方法： Handler.mCallback.handleMessage(msg) ，
    //优先级仅次于1；
    //Handler的默认方法： Handler.handleMessage(msg) ，优先级最低。
    //对于很多情况下，消息分发后的处理方法是第3种情况，
    //即 Handler.handleMessage() ，一般地往往通过覆写该方法从而实现自己的业务
    //逻辑。
}
