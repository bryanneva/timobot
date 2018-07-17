package com.timobot.matchhandler

interface Event {
    fun execute(slackCommand : SlackCommand): String
}