package com.example.domain

import com.example.domain.repository.LeaguesRepository
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetEventsUseCaseTest : BaseTest() {

    @MockK
    lateinit var leaguesRepository: LeaguesRepository

}