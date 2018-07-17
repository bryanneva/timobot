package com.timobot.matchhandler

import org.springframework.web.bind.annotation.*

@RestController
class CommandController(private var match: Match) {

    @PostMapping("/command")
    fun handleCommand(@ModelAttribute slackCommand: SlackCommand): String {
        val command = slackCommand.text.split(" ");
        when (command[0]) {
            "match" -> return match.execute(slackCommand)
            else -> return "Invalid command"
        }
    }
}