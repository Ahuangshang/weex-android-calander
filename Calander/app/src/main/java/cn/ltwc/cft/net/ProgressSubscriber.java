package cn.ltwc.cft.net;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.utils.ToastUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.LOADING_VIEW_DELAYED;


/**
 * ProgressSubscriber
 * Created by LZL on 2017/1/23.
 */
@SuppressWarnings("unchecked")
public class ProgressSubscriber<T> extends Subscriber<T> {
    private Action1<T> nextAction;
    private Action1<Throwable> errorAction;
    private boolean showProgress;//是否显示加载框
    private long startTime;
    private boolean showErrorToast = true;
    private boolean hideProgress = true;//成功加载完成，是否隐藏加载框

    public ProgressSubscriber(Action1<T> nextAction) {
        this.nextAction = nextAction;
    }

    public ProgressSubscriber(Action1<T> nextAction, Action1<Throwable> errorAction) {
        this.nextAction = nextAction;
        this.errorAction = errorAction;
    }

    public ProgressSubscriber(Action1<T> nextAction, boolean showProgress) {
        this.showProgress = showProgress;
        this.nextAction = nextAction;
    }

    public ProgressSubscriber(Action1<T> nextAction, Action1<Throwable> errorAction, boolean showProgress) {
        this.nextAction = nextAction;
        this.errorAction = errorAction;
        this.showProgress = showProgress;
    }

    public ProgressSubscriber(Action1<T> nextAction, Action1<Throwable> errorAction, boolean showProgress, boolean hideProgress) {
        this.nextAction = nextAction;
        this.errorAction = errorAction;
        this.showProgress = showProgress;
        this.hideProgress = hideProgress;
    }

    @Override
    public void onStart() {
        super.onStart();
        startTime = System.currentTimeMillis();
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(final Throwable e) {
        long expendTime = System.currentTimeMillis() - startTime;
        rx.Observable.timer(expendTime >= LOADING_VIEW_DELAYED ? 0 : LOADING_VIEW_DELAYED - expendTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (isShowErrorToast()) {
                            if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
                                ToastUtil.showMessage("网络中断，请检查您的网络状态");
                            } else {
                                ToastUtil.showMessage("请求异常，请稍后重试");
                            }
                        }
                        disMissProgressDialog();
                        if (errorAction != null) {
                            if (Utils.isNull(e)) {
                                errorAction.call(new Throwable("服务器未知错误"));
                            }
                            errorAction.call(e);
                        }
                    }
                });

    }


    @Override
    public void onNext(final T t) {
        long expendTime = System.currentTimeMillis() - startTime;
        rx.Observable.timer(expendTime >= LOADING_VIEW_DELAYED ? 0 : LOADING_VIEW_DELAYED - expendTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (hideProgress) {
                            disMissProgressDialog();
                        }
                        if (nextAction != null && !Utils.isNull(t)) {
                            nextAction.call(t);
                        }
                    }
                });
    }


    private void showProgressDialog() {
        if (showProgress) {
            MyApplication.getInstance().showProgressDialog();
        }
    }

    private void disMissProgressDialog() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
        MyApplication.getInstance().disMissProgressDialog();
    }

    public boolean isShowErrorToast() {
        return showErrorToast;
    }

    public ProgressSubscriber setShowErrorToast(boolean showErrorToast) {
        this.showErrorToast = showErrorToast;
        return this;
    }
}
