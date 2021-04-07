package org.shizhenqiang.framework.event;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        // 添加事件监听者
        observable.addObserver(new EventObserver());
        // 发送事件
        observable.notifyObservers("hello, world");
    }

    static class EventObservable extends Observable {
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static  class EventObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            EventObject event = (EventObject)arg;
            System.out.println("收到事件：" + event.getSource());
        }
    }
}
