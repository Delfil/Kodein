plugins {
    kodein.library.android
}

dependencies {
    api(projects.framework.android.kodeinDiFrameworkAndroidX)

    implementation(libs.android.x.appcompat)
    implementation(libs.android.x.fragment.ktx)
    implementation(libs.android.x.lifecycle.viewmodel.ktx)
}

kodeinUpload {
    name = "Kodein-Framework-AndroidX-ViewModel"
    description = "Kodein extensions for AndroidX ViewModel"
}
