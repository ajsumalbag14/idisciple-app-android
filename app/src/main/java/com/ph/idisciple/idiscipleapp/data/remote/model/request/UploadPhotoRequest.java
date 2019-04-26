package com.ph.idisciple.idiscipleapp.data.remote.model.request;

import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseRequestMap;

public class UploadPhotoRequest extends BaseRequestMap {

    public void setUserId(String userId) {
        mParams.put("user_id", userId);
    }

    public void setFileName(String fileName) {
        mParams.put("filename", fileName);
    }

    public void setImage(String fileName) {
        mParams.put("base64_image", fileName);
    }

}
