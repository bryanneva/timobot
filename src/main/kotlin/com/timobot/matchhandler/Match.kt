package com.timobot.matchhandler

import org.springframework.stereotype.Service

@Service
class Match : Event {
    data class Player(val name: String, val id: String? = null)

    override fun execute(slackCommand: SlackCommand): String {
        val command = slackCommand.text.split(" ");

        val name = slackCommand.user_name
        val id = slackCommand.user_id

        val opponent = command[1]

        return "match"
    }
}