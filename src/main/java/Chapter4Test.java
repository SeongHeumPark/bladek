import common.CommonUtils;
import common.Log;
import data.Shape;
import helper.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author seongheum.park
 */
public class Chapter4Test {
    public static void main(String[] args) {
        Chapter4Test test = new Chapter4Test();
//        test.interval1();
//        test.interval2();
//        test.timer();
//        test.range();
//        test.intervalRange();
//        test.handmadeIntervalRange(1, 10);
//        test.defer();
//        test.repeat();
//        test.heartbeat();
//        test.concatMap();
//        test.switchMap();
//        test.groupBy1();
//        test.groupBy2();
        test.scan();
    }

    private void interval1() {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(data -> (data + 1) * 100)
                .take(5);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    private void interval2() {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(data -> data + 100)
                .take(5);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    private void timer() {
        CommonUtils.exampleStart();
        Observable<String> source = Observable.timer(500L, TimeUnit.MILLISECONDS)
                .map(notUsed -> {
                    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                });
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    private void range() {
        Observable<Integer> source = Observable.range(1, 10)
                .filter(number -> number % 2 == 0);
        source.subscribe(Log::i);
    }

    private void intervalRange() {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.intervalRange(1, 5, 100L, 100L, TimeUnit.MILLISECONDS);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    private void handmadeIntervalRange(final int n, final int m) {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(100L, TimeUnit.MICROSECONDS)
                .map(data -> data + n)
                .take(m)
                .filter(data -> data % 2 == 0);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    private void defer() {
        Callable<Observable<String>> supplier = () -> getObservable();
        Observable<String> source = Observable.defer(supplier);

        source.subscribe(val -> Log.i("Subscriber #1:" + val));
        source.subscribe(val -> Log.i("Subscriber #2:" + val));
        CommonUtils.exampleComplete();
    }

    private Observable<String> getObservable() {
        Iterator<String> colors = Arrays.asList("1", "3", "5", "6").iterator();

        if (colors.hasNext()) {
            String color = colors.next();
            return Observable.just(
                    Shape.getString(color, Shape.BALL),
                    Shape.getString(color, Shape.RECTANGLE),
                    Shape.getString(color, Shape.PENTAGON));
        } else {
            return Observable.empty();
        }
    }

    private void repeat() {
        String[] balls = { "1", "3", "5" };

        Observable<String> source = Observable.fromArray(balls)
                .repeat(3);
        source.doOnComplete(() -> Log.d("onComplete"))
                .subscribe(Log::i);
    }

    private void heartbeat() {
        CommonUtils.exampleStart();
        String serverUrl = "https://api.github.com/zen";

        Observable.timer(2, TimeUnit.SECONDS)
                .map(val -> serverUrl)
                .map(OkHttpHelper::get)
                .repeat()
                .subscribe(res -> Log.it("Ping Result : " + res));
        CommonUtils.sleep(10000);
    }

    private void concatMap() {
        CommonUtils.exampleStart();
        String[] balls = { "1", "3", "5" };

        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .concatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .map(notUsed -> "<" + ball + ">")
                    .take(2));
        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    private void switchMap() {
        CommonUtils.exampleStart();
        String[] balls = { "1", "3", "5" };

        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(Log::dt)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> "<" + ball + ">")
                        .take(2));
        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    private void groupBy1() {
        String[] objs = { "6", "4", "2-T", "2", "6-T", "4-T" };
        Observable<GroupedObservable<Shape, String>> source = Observable
                .fromArray(objs)
                .groupBy(Shape::fromString);
        source.subscribe(obj -> obj.subscribe(
                val -> System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val)));
    }

    private void groupBy2() {
        String[] objs = { "6", "4", "2-T", "2", "6-T", "4-T" };
        Observable<GroupedObservable<Shape, String>> source = Observable
                .fromArray(objs)
                .groupBy(Shape::fromString);
        source.subscribe(obj -> obj.filter(
                val -> obj.getKey() == Shape.BALL).subscribe(
                        val -> System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val)));
    }

    private void scan() {
        String[] balls = { "1", "3", "5" };
        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(Log::i);
    }
}
