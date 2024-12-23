package com.erdgn.yemeksiparisapp.di

import com.erdgn.yemeksiparisapp.data.datasource.YemeklerDataSource
import com.erdgn.yemeksiparisapp.data.repo.YemeklerRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerDataSource(collectionYemekler:CollectionReference):YemeklerDataSource{
        return YemeklerDataSource(collectionYemekler)
    }

    @Provides
    @Singleton
    fun provideYemeklerRepository(yds:YemeklerDataSource):YemeklerRepository{
        return YemeklerRepository(yds)
    }

    @Provides
    @Singleton
    fun provideCollectionReference():CollectionReference{
        return Firebase.firestore.collection("Yemekler")
    }


}