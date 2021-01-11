package com.example.android.dagger.di
import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import com.example.android.dagger.settings.SettingsActivity
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

//AppComponent is the Dagger Graph of dependencies

//we include the modules annotation to let Dagger know about the StorageModule
//in our example Dagger will now know to look at this module when it needs to create an object
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    // since Context is provided by the Android system itself, we can only pass it into
    // methods that require it. To do so, we need a component factory with the
    // @BindsInstance annotation

    //This interface annotation implies that inside is a method that returns a
    // component type (i.e. AppComponent) and has param Context annotated with BidnsInstance

    //BindsInstance tells Dagger that it needs to add that instance to the graph, and whenever
    // Context is required, it provides that instance of Context

    //BindsInstance is used for objects constructed outside of the graph
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    //expose the RegistrationComponent factory by declaring a function with that class
    // as return type.

    //There are two different ways to interact with the Dagger graph:
    //
    // 1. Declaring a function that returns Unit and takes a class as a parameter allows field
    // injection in that class (e.g. fun inject(activity: MainActivity)).
    // 2. Declaring a function that returns a type allows retrieving types from the graph
    // (e.g. fun registrationComponent(): RegistrationComponent.Factory).
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory

    fun userManager(): UserManager

    //fun inject(activity: RegistrationActivity)
    //fun inject(fragment: EnterDetailsFragment)
    //fun inject(fragment: TermsAndConditionsFragment)
    //fun inject(activity: MainActivity)
    //fun inject(activity: SettingsActivity)
}