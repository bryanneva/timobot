package com.timobot.matchhandler.http

import com.timobot.matchhandler.core.events.Match
import org.springframework.web.bind.annotation.*

@RestController
class CommandController(private val match: Match) {

    @PostMapping("/command")
    fun handleCommand(@ModelAttribute slackCommand: SlackCommand): String {
        val command = slackCommand.text.split(" ");
        when (command[0]) {
            "match" -> return match.execute(slackCommand)
            else -> return "Invalid command"
        }
    }
}