package com.netty.demo.vertx;

import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RxJavaTest {

    public static void main(String[] args) throws InterruptedException {

        Observable.merge(Observable.just(1, 2, 3, 4), Observable.just(5, 6, 7, 8))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        log.info("accept: merge :" + integer);
                    }
                });
        Thread.sleep(3000000);
    }
}
