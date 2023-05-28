package okhttp;
import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;

import okhttp3.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTestOkHttp {
    Gson gson = new Gson();
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginSuccess() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder().username("noa@gmail.com").password("Nnoa12345$").build();

        RequestBody requestBody= RequestBody.create(gson.toJson(auth),JSON);
        Request request= new  Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        AuthResponseDto authResponseDto =
                gson.fromJson(response.body().string(),AuthResponseDto.class);
        System.out.println(authResponseDto.getAccessToken());

    }
}
