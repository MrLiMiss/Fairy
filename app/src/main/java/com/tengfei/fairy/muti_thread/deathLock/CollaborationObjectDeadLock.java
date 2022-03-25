package com.tengfei.fairy.muti_thread.deathLock;

import android.graphics.Point;
import android.media.Image;

import java.util.HashSet;
import java.util.Set;

import okhttp3.Dispatcher;

/**
 * @ Description :协作对象之间发生的死锁
 * 有时，死锁并不会那么明显，比如两个相互协作的类之间的死锁，比如下面的代码
 * ：一个线程调用了Taxi对象的setLocation方法，另一个线程调用了Dispatcher对 象的getImage方法。
 * 此时可能会发生，第一个线程持有Taxi对象锁并等待 Dispatcher对象锁，
 * 另一个线程持有Dispatcher对象锁并等待Taxi对象锁。
 * @ Author 李腾飞
 * @ Time 2022/3/25   11:31 AM
 * @ Version :
 */
class CollaborationObjectDeadLock {

    class Taxi {
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination))
                dispatcher.notifyAvailable(this);//外部调用方法，可能等 待Dispatcher对象锁
        }
    }


    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<Taxi>();
            availableTaxis = new HashSet<Taxi>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi t : taxis)
                image.drawMarker(t.getLocation());
            //外部调用方法，可能 等待Taxi对象锁 return image;
            return image;
        }
    }


    class Image{
        public Image(){}

        /**
         * class Image 仅用于测试，无意义
         * @param point
         * @return
         */
        public  Image drawMarker(Point point){
            return new Image();
        }

    }


}
