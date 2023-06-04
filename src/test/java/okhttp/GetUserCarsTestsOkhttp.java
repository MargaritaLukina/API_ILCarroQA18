package okhttp;

import com.google.gson.Gson;
import dto.CarDto;
import dto.GetMyCarsDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserCarsTestsOkhttp {
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    //String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibm9hQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjg1ODg5NzQ1LCJpYXQiOjE2ODUyODk3NDV9.gPY6fM5kRTXA67zVZ4_HrsvNDh7gVeLGXHA5yxRX7zU";
    String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibm9hQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjg2NDk2NjEwLCJpYXQiOjE2ODU4OTY2MTB9.-NTIMP2-wppMxKdAl17NCcgTmWu42vmQhX5VW-pjVys";

    @Test
    public void successGetUserCars() throws IOException {

        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/cars/my")
                .header("Authorization",token)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        GetMyCarsDto carsDto = gson.fromJson(response.body().string(),GetMyCarsDto.class);

        List<CarDto> cars = carsDto.getCars();
        for(CarDto car: cars){
            System.out.println(car.getSerialNumber());
            System.out.println("*****");
        }


    }
}
