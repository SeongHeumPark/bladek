import common.CommonUtils;
import common.Log;
import data.Shape;
import helper.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author seongheum.park
 */
public class Chapter5Test {
    public static void main(String[] args) {
//        Chapter5Test test = new Chapter5Test();
//        test.marbleDiagram();
//        test.newThread();
//        test.computation();
//        test.io();
//        test.trampoline();
//        test.single();
//        test.executor();
//        test.run();
//        test.callbackHell();
//        test.escapeUsingConcat();
//        test.escapeUsingZip();

        OpenWeatherMap weather = new OpenWeatherMap();
        weather.run();
    }

    private void marbleDiagram() {
        String[] objs = { "1-S", "2-T", "3-P" };
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> Log.v("Original data = " + data))
                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.newThread())
                .map(Shape::flip);
        source.subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    private void newThread() {
        String[] orgs = { "1", "3", "5" };
        Observable.fromArray(orgs)
                .doOnNext(data -> Log.v("Original data = " + data))
                .map(data -> "<<" + data + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::i);
        CommonUtils.sleep(500);

        Observable.fromArray(orgs)
                .doOnNext(data -> Log.v("Original data = " + data))
                .map(data -> "##" + data + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    private void computation() {
        String[] orgs = { "1", "3", "5" };
        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        source.map(item -> "<<" + item + ">>")
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i);

        source.map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    private void io() {
        String root = "c:\\";
        File[] files = new File(root).listFiles();
        Observable<String> source = Observable.fromArray(files)
                .filter(f -> !f.isDirectory())
                .map(f -> f.getAbsolutePath())
                .subscribeOn(Schedulers.io());

        source.subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    private void trampoline() {
        String[] orgs = { "1", "3", "5" };
        Observable<String> source = Observable.fromArray(orgs);

        source.subscribeOn(Schedulers.trampoline())
                .map(data -> "<<" + data + ">>")
                .subscribe(Log::i);

        source.subscribeOn(Schedulers.trampoline())
                .map(data -> "##" + data + "##")
                .subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    private void single() {
        Observable<Integer> numbers = Observable.range(100, 5);
        Observable<String> chars = Observable.range(0, 5)
                .map(CommonUtils::numberToAlphabet);

        numbers.subscribeOn(Schedulers.single())
                .subscribe(Log::i);
        chars.subscribeOn(Schedulers.single())
                .subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    private void executor() {
        final int THREAD_NUM = 10;

        String[] data = { "1", "3", "5" };
        Observable<String> source = Observable.fromArray(data);
        Executor executor = Executors.newFixedThreadPool(THREAD_NUM);

        source.subscribeOn(Schedulers.from(executor))
                .subscribe(Log::i);
        source.subscribeOn(Schedulers.from(executor))
                .subscribe(Log::i);
        CommonUtils.sleep(500);
    }

    private static final String URL_README = "https://raw.github.com/SeongHeumPark/GearWallet/master/README.md";

    private static final OkHttpClient client = new OkHttpClient();

    private void run() {
        Request request = new Request.Builder()
                .url(URL_README)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(response.body().string());
            }
        });
    }

    private static final String GITHUB_ROOT = "https://raw.github.com/SeongHeumPark/GearWallet/master";
    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = GITHUB_ROOT + "/GearWallet - Tizen/config.xml";

    private Callback onSuccess = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i(response.body().string());
        }
    };

    private void callbackHell() {
        Request request = new Request.Builder()
                .url(FIRST_URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(response.body().string());

                Request request = new Request.Builder()
                        .url(SECOND_URL)
                        .build();
                client.newCall(request).enqueue(onSuccess);
            }
        });
    }

    private void escapeUsingConcat() {
        CommonUtils.exampleStart();
        Observable<String> source = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get)
                .concatWith(Observable.just(SECOND_URL)
                    .map(OkHttpHelper::get));
        source.subscribe(Log::it);
        CommonUtils.sleep(5000);
    }

    private void escapeUsingZip() {
        CommonUtils.exampleStart();
        Observable<String> first = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);
        Observable<String> second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);

        Observable.zip(first, second, (a, b) -> ("\n>> " + a + "\n>> " + b))
                .subscribe(Log::it);
        CommonUtils.sleep(5000);
    }

    private static class OpenWeatherMap {
        private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=Seoul&APPID=";

        private void run() {
            Observable<String> source = Observable.just(URL + "4357f7a7cef98196b08534f15b148f33")
                    .map(OkHttpHelper::get)
                    .subscribeOn(Schedulers.io());

            Observable<String> temperature = source.map(this::parseTemperature);
            Observable<String> city = source.map(this::parseCityName);
            Observable<String> country = source.map(this::parseCountry);

            CommonUtils.exampleStart();

            Observable.zip(temperature, city, country, (a, b, c) -> "\n" + a + "\n" + b + "\n" + c)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(Log::it);

            CommonUtils.sleep(1000);
        }

        private String parseTemperature(String json) {
            return parse(json, "\"temp\":[0-9]*.[0-9]");
        }

        private String parseCityName(String json) {
            return parse(json, "\"name\":\"[a-zA-Z]*\"");
        }

        private String parseCountry(String json) {
            return parse(json, "\"country\":\"[a-zA-Z]*\"");
        }

        private String parse(String json, String regex) {
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(json);
            if (match.find()) {
                return match.group();
            }

            return "N/A";
        }
    }
}