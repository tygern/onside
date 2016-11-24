package com.tygern.onside

import io.damo.aspen.Test
import io.damo.aspen.spring.SpringTestTreeRunner
import io.damo.aspen.spring.injectValue
import okhttp3.OkHttpClient
import okhttp3.Request
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@RunWith(SpringTestTreeRunner::class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
class OnsideApplicationTests : Test({

    val port = injectValue("local.server.port", Int::class)
    val client = OkHttpClient()

    test("GET /health") {
        val request = Request.Builder()
                .url("http://localhost:$port/health")
                .build()

        val response = client.newCall(request).execute()

        val status = response.code()
        assertThat(status).isEqualTo(200)
    }
})
