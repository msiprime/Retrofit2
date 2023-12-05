package com.example.testapplication.data

import com.example.testapplication.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(
    private val api: Api
) : ProductRepository {
    override suspend fun getProductsList(): Flow<Result<List<Product>>> {

        return flow {
            val productFromApi = try {
                api.getProductsList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading Products"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading Products"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading Products"))
                return@flow
            }
            emit(Result.Success(productFromApi.products))
        }
    }
}