package com.timobot.matchhandler.core.events

import com.timobot.matchhandler.core.models.Player
import com.timobot.matchhandler.core.strategies.Ranker
import com.timobot.matchhandler.http.Event
import com.timobot.matchhandler.http.SlackCommand
import org.springframework.stereotype.Service

@Service
class Match(private val ranker: Ranker) : Event {

    override fun execute(slackCommand: SlackCommand): String {
        val command = slackCommand.text.split(" ");

        val player = Player(slackCommand.user_name, id = slackCommand.user_id)
        val opponent = Player(command[1])

        this.ranker.compare(listOf(player, opponent), opponent)

        return "New ranks: ${player.name}: ${player.rank}; ${opponent.name}: ${opponent.rank}"
//        return result.toString()
    }
}