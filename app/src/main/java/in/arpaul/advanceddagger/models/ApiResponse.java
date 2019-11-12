package in.arpaul.advanceddagger.models;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import retrofit2.Response;

public class ApiResponse<T> {

    public static ApiIsLoading createLoadingResponse() {
        return new ApiIsLoading();
    }

    public static ApiErrorResponse create(Throwable error)  {
        return new ApiErrorResponse(error);
    }

    public static ApiResponse create(Response response) {

        if (response.isSuccessful()) {
            Object body = response.body();
            if (body == null || response.code() == 204) {
                return new ApiEmptyResponse();
            } else {
                return new ApiSuccessResponse(body);
            }
        } else {
            String msg = response.errorBody().toString();
            String errorMsg;
            if (TextUtils.isEmpty(msg)) {
                errorMsg = response.message();
            } else {
                errorMsg = msg;
            }
            return new ApiErrorResponse(new Exception(errorMsg));
        }
    }

    public static class ApiIsLoading<T> extends ApiResponse<T> {}

    public static class ApiEmptyResponse<T> extends ApiResponse<T>{}

    public static class ApiErrorResponse<T> extends ApiResponse<T> {
        private Throwable error;
        public ApiErrorResponse(Throwable errorMessage){
            this.error = errorMessage;
        }

        public Throwable getError() {
            return error;
        }
    }

    public static class ApiSuccessResponse<T> extends ApiResponse<T> {
        private T body;
        public ApiSuccessResponse(T body){
            this.body = body;
        }

        public T getBody() {
            return body;
        }
    }
}
