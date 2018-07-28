package com.timobot.matchhandler.http

interface Event {
    fun execute(slackCommand : SlackCommand): String
}