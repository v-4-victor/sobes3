/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.sobes2.network

import com.example.sobes2.Results
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://kudago.com/public-api/v1.4/news/"
@Module
object ServiceModule{
    @Provides
    internal fun getMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    @Provides
    internal fun retrofit(moshi: Moshi):MarsApiServiceDI = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
        .create(MarsApiServiceDI::class.java)
}

interface MarsApiServiceDI {
    /**
     * Returns a Coroutine [List] of [MarsProperty] which can be fetched in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET(".")
    suspend fun getProperties(@Query("fields") type: String): Results
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
