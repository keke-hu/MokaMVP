package com.cdwx.moka.injector.moudule.net;


import com.cdwx.moka.injector.qualifier.MmoteUrl;
import com.cdwx.moka.net.APIService;
import com.cdwx.moka.utils.APIServiceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class RequestFactoryModule extends BaseRetrofitMoudule {


    @Singleton
    @Provides
    @MmoteUrl
    Retrofit provideMmoteRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, BASE_URL);
    }

    @Singleton
    @Provides
    APIService provideAPIService(@MmoteUrl Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }


    @Provides
    @Singleton
    APIServiceUtils provideAPIServiceUtil(APIService apiService) {
        return new APIServiceUtils(apiService);
    }


}
