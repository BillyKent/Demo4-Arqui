package com.software.miedo.demo4.data;

import com.software.miedo.demo4.model.ComentarioResponse;
import com.software.miedo.demo4.model.PostComentario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ComentariosRequestAPI {

    @Headers("Content-Type: application/json")
    @POST("/alfresco/api/-default-/public/alfresco/versions/1/nodes/b5404121-f800-468f-9d7d-512ee26bef51/comments")
    Call<ComentarioResponse> postComment(@Body PostComentario comment);

}
