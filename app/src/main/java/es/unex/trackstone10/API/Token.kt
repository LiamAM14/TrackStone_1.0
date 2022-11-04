package es.unex.trackstone10.API

data class Token(
    val access_token: String?,
    val token_type: String?,
    val expires_in: Int?,
    val scope: String?
)
