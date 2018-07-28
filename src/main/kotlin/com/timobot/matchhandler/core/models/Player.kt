package com.timobot.matchhandler.core.models

data class Player(val name: String, var rank: Int = 1000, val id: String? = null) {
    enum class Result(val value: Int) {

        WON(1),
        LOST(0)
    }

    private var gamesPlayed: Int = 0

    fun getGamesPlayed(): Int {
        return gamesPlayed
    }

    fun playedGame() {
        gamesPlayed++
    }

    fun wonGame(opponent: Player) {
        playedGame()
        val expectedScore = calculateExpectedScore(rank, opponent.rank)
        val score = Result.WON.value - expectedScore
        val kFactor = getKFactor()
        val delta = kFactor * score

        rank += delta.toInt()
    }

    fun lostGame(opponent: Player) {
        playedGame()
        val expectedScore = calculateExpectedScore(opponent.rank, rank)
        val score = Result.LOST.value - expectedScore
        val kFactor = getKFactor()
        val delta = kFactor * score

        rank += delta.toInt()
    }

    private fun calculateExpectedScore(winnerRank: Int, loserRank: Int): Double {
        val exponent = (winnerRank - loserRank) / 400
        val denominator = 1 + Math.pow(10.toDouble(), exponent.toDouble())

        return 1 / denominator
    }

    private fun getKFactor(): Int {
        if (gamesPlayed < 30) {
            return 40
        }

        if (rank < 2400) {
            return 20
        }

        return 10
    }
}