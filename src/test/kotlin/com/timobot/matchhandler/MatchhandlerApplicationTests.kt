package com.timobot.matchhandler

import com.timobot.matchhandler.http.CommandController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest
class MatchhandlerApplicationTests {

	@Autowired
	private val controller: CommandController? = null

	@Test
	fun contextLoads() {
		assertThat(controller).isNotNull
	}

}
