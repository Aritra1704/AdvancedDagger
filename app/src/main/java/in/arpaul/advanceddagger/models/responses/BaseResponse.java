package in.arpaul.advanceddagger.models.responses;

import com.google.gson.JsonObject;

public class BaseResponse {

    private String ok = "";
    private JsonObject response;
    private JsonObject error;

    public BaseResponse(String ok, JsonObject response, JsonObject error) {
        this.ok = ok;
        this.response = response;
        this.error = error;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public JsonObject getResponse() {
        return response;
    }

    public void setResponse(JsonObject response) {
        this.response = response;
    }

    public JsonObject getError() {
        return error;
    }

    public void setError(JsonObject error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "ok='" + ok + '\'' +
                ", response=" + response +
                ", error=" + error +
                '}';
    }
}
