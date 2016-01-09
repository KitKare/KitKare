package kitkare.kitkare.app.services;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AccountService {
    OkHttpClient client = new OkHttpClient();

//    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");
//
//    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    public String doPostRequest(String url) throws Exception  {
        FormEncodingBuilder builder = new FormEncodingBuilder()
                //.add("Content-Type", "application/x-www-form-urlencoded")
                .add("email", "meme@me.me")
                .add("password", "123456")
                .add("confirmPassword", "123456");
        RequestBody body = builder.build();

        //RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                //.header("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().toString());
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        System.out.println(response.body().string());

        return response.body().string();
    }
}
