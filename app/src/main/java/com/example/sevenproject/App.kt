package com.example.sevenproject

import android.app.Application
import com.example.sevenproject.data.local.AppDataBase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltAndroidApp
class App : Application()