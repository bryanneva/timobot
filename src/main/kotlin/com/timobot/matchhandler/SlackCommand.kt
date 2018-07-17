package com.timobot.matchhandler

data class SlackCommand(
        val token: String = "",
        val team_id: String = "",
        val team_domain: String = "",
        val channel_id: String = "",
        val channel_name: String = "",
        val user_id: String = "",
        val user_name: String = "",
        val command: String = "",
        val text: String = "",
        val response_url: String = ""
)