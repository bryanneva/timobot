package com.timobot.matchhandler.core.strategies

import com.timobot.matchhandler.core.models.Player
import org.springframework.stereotype.Service

@Service
class EloRanker : Ranker {
    override fun compare(players: List<Player>, winner: Player): List<Int> {
        if(players.size > 2) {
            throw IllegalArgumentException()
        }

        if(winner !in players) {
            throw Exception()
        }

        val loser = players.filter { player -> !player.name.equals(winner.name) }[0]
        winner.wonGame(loser)
        loser.lostGame(winner)

        return players.map { player -> player.rank }
    }

}