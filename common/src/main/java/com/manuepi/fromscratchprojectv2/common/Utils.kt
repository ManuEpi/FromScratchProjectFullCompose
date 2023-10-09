package com.manuepi.fromscratchprojectv2.common

import retrofit2.Response
import timber.log.Timber

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object Home : Screens("home_screen")
}

sealed class NetworkResponse<T : Any> {
    class Success<T : Any>(val data: T) : NetworkResponse<T>()
    class Error<T : Any>(val message: String?) : NetworkResponse<T>()
}

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkResponse<T> {
    return try {
        val result = call.invoke()
        if (result.isSuccessful) {
            NetworkResponse.Success(data = result.body()!!)
        } else
            NetworkResponse.Error(message = "Une erreur est survenue")
    } catch (t: Throwable) {
        Timber.e(t)
        NetworkResponse.Error(message = "Une erreur est survenue")
    }
}

fun formatText(numberOfNews: Int): String {

    return if (numberOfNews <= 0)
        "Nous n'avons pas trouvé de résultat pour le mot bitcoin"
    else
        "Nous vous avons trouvé $numberOfNews résultats pour le mot bitcoin"
}