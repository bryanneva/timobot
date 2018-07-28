package com.timobot.matchhandler.core.strategies

import com.timobot.matchhandler.core.models.Player

interface Ranker {
    fun compare(players: List<Player>, winner: Player): List<Int>
}