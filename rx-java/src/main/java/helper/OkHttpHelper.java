package helper;

import common.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author seongheum.park
 */
public class OkHttpHelper {
    private static OkHttpClient client = new OkHttpClient();

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response res = client.newCall(request).execute();
            return res.body().string();
        } catch (IOException e) {
            Log.e(e.getMessage());
            throw e;
        }
    }
}
