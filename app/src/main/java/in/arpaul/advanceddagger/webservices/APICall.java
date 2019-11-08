package in.arpaul.advanceddagger.webservices;

import in.arpaul.advanceddagger.models.ApiResponse;
import in.arpaul.advanceddagger.models.requests.LoginRequest;
import in.arpaul.advanceddagger.models.responses.UserResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APICall {
    @POST("/login")
    public Observable<ApiResponse<UserResponse>> performLogin(@Header("x-zippr-api-key") String apikey, @Body LoginRequest userId);
}
