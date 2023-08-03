package toniwar.projects.neurogallery.domain.entities

sealed interface NetworkResult<T>

class NetworkSuccess<D>(val networkData: D): NetworkResult<D>

class NetworkError<E>(val errorCode: Int, val errorMessage: String): NetworkResult<E>

class NetworkException<T>(val throwable: Throwable): NetworkResult<T>