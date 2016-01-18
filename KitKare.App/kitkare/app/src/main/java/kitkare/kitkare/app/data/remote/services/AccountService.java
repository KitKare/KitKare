package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import kitkare.kitkare.app.viewModels.LoginViewModel;
import kitkare.kitkare.app.viewModels.RegisterUserViewModel;

public class AccountService extends BaseService {
    public String register(String url, RegisterUserViewModel user) throws Exception {
        FormEncodingBuilder builder = new FormEncodingBuilder()
                .add("email", user.email)
                .add("password", user.password)
                .add("confirmPassword", user.confirmPassowrd);

        return this.sendRequest(url, builder);
    }

    public String login(String url, LoginViewModel user) throws Exception {
        FormEncodingBuilder builder = new FormEncodingBuilder()
                .add("username", user.username)
                .add("password", user.password)
                .add("grant_type", "password");

        return this.sendRequest(url, builder);
    }

    private String sendRequest(String url, FormEncodingBuilder builder) throws Exception {
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = super.client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Oops, something went wrong! " + response);
        }

        return response.body().string();
    }
}