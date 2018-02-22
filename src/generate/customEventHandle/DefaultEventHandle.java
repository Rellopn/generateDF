package generate.customEventHandle;

import generate.customObserver.CustomObserver;

public class DefaultEventHandle implements CustomObserver {
    @Override
    public void update(String eventMessage) {
        System.out.println("默认的事件监听期收到了消息，消息的类型是：" + eventMessage);
    }
}
