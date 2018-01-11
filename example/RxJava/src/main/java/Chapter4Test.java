import common.CommonUtils;
import common.Log;
import data.Shape;
import helper.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.observables.GroupedObservable;

import java.text.DecimalFormat;
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
//        test.scan();
//        test.zip();
//        test.zipNumbers();
//        test.zipInterval();
//        test.electricBillV1();
//        test.electricBillV2();
//        test.zipWith();
//        test.combineLatest();
        test.concat();
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

    private void zip() {
        Shape[] shapes = { Shape.BALL, Shape.PENTAGON, Shape.STAR };
        String[] coloredTriangles = { "2-T", "6-T", "4-T" };
        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix);
        source.subscribe(Log::i);
    }

    private void zipNumbers() {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                (a, b, c) -> a + b + c);
        source.subscribe(Log::i);
    }

    private void zipInterval() {
        Observable<String> source = Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (value, i) -> value);

        CommonUtils.exampleStart();
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    private int index = 0;

    private void electricBillV1() {
        Integer[] data = {100, 300};

        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(val -> {
                    if (val <= 200) return 910;
                    else if (val <= 400) return 1600;
                    else return 7300;
                });

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(val -> {
                    double series1 = Math.min(200, val) * 93.3;
                    double series2 = Math.min(200, Math.max(val - 200, 0)) * 187.9;
                    double series3 = Math.min(0, Math.max(val - 400, 0)) * 280.65;
                    return (int) (series1 + series2 + series3);
                });

        Observable<Integer> source = Observable.zip(
                basePrice,
                usagePrice,
                (v1, v2) -> v1 + v2);

        source
            .map(val -> new DecimalFormat("#,###").format(val))
            .subscribe(val -> {
                StringBuilder sb = new StringBuilder();
                sb.append("Usage: " + data[index] + " kWh => ");
                sb.append("Price: " + val + "원");
                Log.i(sb.toString());

                index++;
            });
    }

    private void electricBillV2() {
        Integer[] data = {100, 300};

        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(val -> {
                    if (val <= 200) return 910;
                    else if (val <= 400) return 1600;
                    else return 7300;
                });

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(val -> {
                    double series1 = Math.min(200, val) * 93.3;
                    double series2 = Math.min(200, Math.max(val - 200, 0)) * 187.9;
                    double series3 = Math.min(0, Math.max(val - 400, 0)) * 280.65;
                    return (int) (series1 + series2 + series3);
                });

        Observable<String> source = Observable.zip(
                Observable.fromArray(data),
                basePrice,
                usagePrice,
                (u, v1, v2) ->
                    "Usage: " + u + " kWh => Price: " + new DecimalFormat("#,###").format(v1 + v2) + "원");
        source.subscribe(Log::i);
    }

    private void zipWith() {
        Observable<Integer> source = Observable.zip(
            Observable.just(100, 200, 300),
            Observable.just(10, 20, 30),
            (a, b) -> a + b)
                .zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
        source.subscribe(Log::i);
    }

    private void combineLatest() {
        Integer[] numbers = { 6, 7, 4, 2 };
        Shape[] shapes = { Shape.DIAMOND, Shape.STAR, Shape.PENTAGON };

        Observable<String> source = Observable.combineLatest(
            Observable.fromArray(numbers)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                    (number, notUsed) -> number),
            Observable.fromArray(shapes)
                .zipWith(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                    (shape, notUsed) -> Shape.getSuffix(shape)),
            (v1, v2) -> v1 + v2);
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    private void concat() {
        Action onCompleteAction = () -> Log.d("onComplete()");

        Integer[] data1 = { 1, 3, 5 };
        Integer[] data2 = { 2, 4, 6 };
        Observable<Integer> source1 = Observable.fromArray(data1)
            .doOnComplete(onCompleteAction);
        Observable<Integer> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
            .map(Long::intValue)
            .map(idx -> data2[idx])
            .take(data2.length)
            .doOnComplete(onCompleteAction);

        Observable<Integer> source = Observable.concat(source1, source2)
            .doOnComplete(onCompleteAction);
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }
}
