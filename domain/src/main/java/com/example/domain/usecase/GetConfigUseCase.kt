package com.example.domain.usecase

import com.example.domain.model.response.Events
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.Iterator
import com.example.domain.usecase.base.IteratorGeneral
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy
)  : IteratorGeneral<Boolean, Any> {

    override fun execute(params: Any): Boolean {
        return leaguesRepository.getConfig()
    }


}