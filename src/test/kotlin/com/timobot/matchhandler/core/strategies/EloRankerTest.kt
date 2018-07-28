package com.timobot.matchhandler.core.strategies

import com.timobot.matchhandler.core.models.Player
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EloRankerTest {
    var subject: EloRanker = EloRanker()

    @BeforeEach
    fun setUp() {
        subject = EloRanker()
    }

    @Test
    fun ranksTwoPlayers() {
        val winner = Player("winner")
        val loser = Player("loser")

        val result = subject.compare(listOf(winner, loser), winner)

        assertThat(result[0]).isEqualTo(1020)
        assertThat(result[1]).isEqualTo(980)
    }

    @Test
    fun winningPlayerHasAHigherRank() {
        val winner = Player("winner")
        val loser = Player("loser")

        val result = subject.compare(listOf(loser, winner), winner)

        assertThat(result[1]).isGreaterThan(result[0])
    }

    @Test
    fun `Throws an error when there are more than 2 players`() {
        val player = Player("player")
        assertThatThrownBy { subject.compare(listOf(player, player, player), player) }
                .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun throwsAnErrorIfWinnerIsNotPartOfPlayers() {
        val player = Player("player")
        assertThatThrownBy { subject.compare(listOf(player, player), Player("stranger")) }
                .isExactlyInstanceOf(Exception::class.java)
    }

    @Test
    fun `Calculates the Elo between two players`() {
        val scoreMatrix = listOf(
                listOf(1000, 1000, 1020, 980),
                listOf(1150, 760, 1170, 757),
                listOf(760, 1150, 780, 1130),
                listOf(1300, 1200, 1320, 1180),
                listOf(1200, 1300, 1220, 1280)
        )

        for (row in scoreMatrix) {
            val winner = Player("winner", row[0], "123")
            val loser = Player("loser", row[1], "456")
            val result = subject.compare(listOf(winner, loser), winner)

            assertThat(result[0]).isEqualTo(row[2])
            assertThat(result[1]).isEqualTo(row[3])
        }
    }
}
