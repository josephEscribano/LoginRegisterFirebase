package com.example.tfgclienttaller.data.room.connect

import android.content.Context
import androidx.room.Room
import com.example.tfgclienttaller.R
import com.example.tfgclienttaller.data.room.WheelFixRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            WheelFixRoomDatabase::class.java,
            context.getString(R.string.db)
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun wheelFixDao(wheelFixRoomDatabase: WheelFixRoomDatabase) = wheelFixRoomDatabase.wheelFixDao()
}