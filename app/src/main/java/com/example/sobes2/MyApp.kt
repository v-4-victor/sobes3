package com.example.sobes2

import android.app.Application
import com.example.sobes2.di.DaggerMyComponent
import com.example.sobes2.di.MyComponent

open class MyApp: Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: MyComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): MyComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerMyComponent.factory().create(applicationContext)
    }
}