package com.app.projectname.infrastructure

data class AuthState(
    var userId: String="",
    var isLoggedIn: Boolean = false,
    var token: String="",
    var refreshToken: String=""
)