package com.example.sobes2.di
import android.content.Context
import com.example.sobes2.network.MarsApiServiceDI
import com.example.sobes2.network.ServiceModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ServiceModule::class])
interface MyComponent {
    fun retrofit(): MarsApiServiceDI
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): MyComponent
    }
}