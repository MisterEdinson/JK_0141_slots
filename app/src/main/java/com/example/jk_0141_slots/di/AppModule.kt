package com.example.jk_0141_slots.di

import android.content.Context
import androidx.room.Room
import com.example.jk_0141_slots.data.local.GameDataBase
import com.example.jk_0141_slots.data.local.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGameDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            GameDataBase::class.java,
            "local_db"
        ).build()

    @Provides
    fun historyDao(appDataBase: GameDataBase): HistoryDao {
        return appDataBase.historyGame()
    }
}