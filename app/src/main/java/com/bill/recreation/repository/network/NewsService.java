package com.bill.recreation.repository.network;

import com.bill.recreation.mvp.entity.GirlData;
import com.bill.recreation.mvp.entity.NewsDetail;
import com.bill.recreation.mvp.entity.NewsSummary;
import com.bill.recreation.mvp.entity.VideoData;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface NewsService {
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewDetail(
            @Header("Cache-Control") String cacheControl,
            @Path("postId") String postId);

    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Url String photoPath);
    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
    // baseUrl 需要符合标准，为空、""、或不合法将会报错

    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getPhotoList(
            @Path("size") int size,
            @Path("page") int page);

    @GET("api/v4/discovery/category")
    Observable<VideoData> getVideosList(
            @Query("start") int page,
            @Query("num") int size);
}
