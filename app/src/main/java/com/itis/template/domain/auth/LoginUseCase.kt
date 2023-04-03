package com.itis.template.domain.auth

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        username: String,
        password: String
    ) = repository.login(username, password)
}