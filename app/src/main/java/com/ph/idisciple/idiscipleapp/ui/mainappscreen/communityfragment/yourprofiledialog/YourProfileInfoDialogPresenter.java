package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.yourprofiledialog;

import android.graphics.Bitmap;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.repository.Attendees.AttendeesRepository;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.Wrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.request.UploadPhotoRequest;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourProfileInfoDialogPresenter implements YourProfileInfoDialogContract.Presenter {

    private YourProfileInfoDialogContract.View mView;
    private UserService mUserService;
    private Bitmap mBitmap;
    private AttendeesRepository mAttendeesRepository;


    private RequestBody bodyData;
    private MultipartBody.Part bodyImage;

    public YourProfileInfoDialogPresenter(YourProfileInfoDialogContract.View view){
        mView = view;
        mUserService = RestClient.getInstance().getUserService();
        mAttendeesRepository = new AttendeesRepository();
    }

    @Override
    public void onLogout(String userId) {
        mUserService.logoutUser(userId).enqueue(new Callback<BaseApi>(){

            @Override
            public void onResponse(Call<BaseApi> call, Response<BaseApi> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        mView.onLogoutSuccess();
                        break;
                    case 422:
                        Gson gson = new Gson();
                        try {

                            BaseApi apiErrorResponse = gson.fromJson(response.errorBody().string(), BaseApi.class);
                            mView.onLogoutFailed(apiErrorResponse.getMessage());

                        } catch (IOException e) {
                            e.printStackTrace();
                            mView.showGenericError();
                        }
                        break;
                    default:
                        mView.showGenericError();
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseApi> call, Throwable t) {
                if (t.getCause() != null && t.getCause().getMessage().contains("ENETUNREACH (Network is unreachable)"))
                    mView.showNoInternetConnection();
                else if ((t.getCause() != null && t.getCause().getMessage().contains("ETIMEDOUT (Connection timed out)")) || t.getMessage().contains("failed to connect"))
                    mView.showTimeoutError();
                else
                    mView.showGenericError();
            }
        });
    }

    @Override
    public void onUploadPhoto(Bitmap bitmap, String fileName, String userId) {
        mBitmap = bitmap;

        // Bitmap to byte[]
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBytes = stream.toByteArray();

        bodyData = RequestBody.create(MediaType.parse("text/plain"), prepareJsonData(fileName, userId));
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), imageBytes);
        bodyImage = MultipartBody.Part.createFormData("image-pic", "image.png", requestFile);

        String encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        UploadPhotoRequest request = new UploadPhotoRequest();
        request.setFileName(fileName);
        request.setUserId(userId);
        request.setImage(encodedImage);

        mUserService
                //.uploadPhoto(request.getParams())
                .uploadPhoto(bodyData, bodyImage)
        .enqueue(new Callback<Wrapper<Profile>>() {
            @Override
            public void onResponse(Call<Wrapper<Profile>> call, Response<Wrapper<Profile>> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        Wrapper<Profile> wrapperResponse = response.body();
                        if(wrapperResponse != null) {
                            mAttendeesRepository.addItem(wrapperResponse.getData());
                            mView.onUploadPhotoSuccess(mBitmap);
                        }else
                            mView.showGenericError();
                        break;
                    case 422:
                        Gson gson = new Gson();
                        try {

                            BaseApi apiErrorResponse = gson.fromJson(response.errorBody().string(), BaseApi.class);
                            mView.onLogoutFailed(apiErrorResponse.getMessage());

                        } catch (IOException e) {
                            e.printStackTrace();
                            mView.showGenericError();
                        }
                        break;
                    default:
                        mView.showGenericError();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Wrapper<Profile>> call, Throwable t) {
                if (t.getCause() != null && t.getCause().getMessage().contains("ENETUNREACH (Network is unreachable)"))
                    mView.showNoInternetConnection();
                else if ((t.getCause() != null && t.getCause().getMessage().contains("ETIMEDOUT (Connection timed out)")) || t.getMessage().contains("failed to connect"))
                    mView.showTimeoutError();
                else
                    mView.showGenericError();
                t.printStackTrace();
            }
        });
    }

    private String prepareJsonData(String fileName, String userId){
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("user_id", userId);
        jsonData.addProperty("filename", fileName);

        Gson gson = new Gson();
        String payload = gson.toJson(jsonData);
        return payload;
    }
}
