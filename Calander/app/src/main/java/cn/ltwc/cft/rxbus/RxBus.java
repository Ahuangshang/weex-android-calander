package cn.ltwc.cft.rxbus;

import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * RxBus
 */
public class RxBus {
    private SerializedSubject<Object, Object> mSubject;
    private HashMap<String, CompositeSubscription> mSubscriptionMap;

    private RxBus() {
        mSubject = new SerializedSubject<>(PublishSubject.create());
    }

    private static class SingletonHolder {
        private static final RxBus INSTANCE = new RxBus();
    }

    public static RxBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 发送事件
     */
    public void post(Event event) {
        mSubject.onNext(event);
    }

    /**
     * 返回指定类型的Observable实例
     */
    private <T> Observable<T> toObservable(final Class<T> type) {
        return mSubject.ofType(type);
    }

    /**
     * 是否已有观察者订阅
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }

    private Subscription doSubscribe(Action1<Event> next) {
        return toObservable(Event.class)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next);
    }

    /**
     * 一个默认的订阅方法
     */
    public <T> Subscription doSubscribe(Class<T> type, Action1<T> next) {
        return toObservable(type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next);
    }

    /**
     * 一个默认的订阅方法
     */
    public <T> Subscription doSubscribe(Class<T> type, Action1<T> next, Action1<Throwable> error) {
        return toObservable(type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    /**
     * 保存订阅后的subscription
     */
    public void addSubscription(Object o, Subscription subscription) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(subscription);
        } else {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            compositeSubscription.add(subscription);
            mSubscriptionMap.put(key, compositeSubscription);
        }
    }

    public void addSubscription(Object o, Action1<Event> next) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = String.valueOf(o.hashCode());
        Subscription subscription = doSubscribe(next);
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(subscription);
        } else {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            compositeSubscription.add(subscription);
            mSubscriptionMap.put(key, compositeSubscription);
        }
    }

    /**
     * 取消订阅
     *
     * @param o 取消对象
     */
    public void unsubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }
        String key = String.valueOf(o.hashCode());
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).unsubscribe();
        }

        mSubscriptionMap.remove(key);
    }
}
