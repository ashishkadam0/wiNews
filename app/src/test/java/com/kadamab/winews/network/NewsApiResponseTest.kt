package com.kadamab.winews.network

import androidx.test.runner.AndroidJUnit4
import com.kadamab.winews.model.Rows
import com.kadamab.winews.repository.MainRepository
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit

@RunWith(AndroidJUnit4::class)
class NewsApiResponseTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(1, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(1, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .build()
        .create(ApiHelper::class.java)

    private val stubb = MainRepository(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testNewsApiSuccessResponse200() {
        mockWebServer.enqueueResponse("news_response.json", 200)

        runBlocking {
            val actual = stubb.getNews()

            val expected = listOf(
                Rows(
                    title = "News1",
                    description = "Wonder Woman comes into...",
                    imageHref = "http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg"
                )
            ).toString()
            assertEquals(expected, actual)
        }
    }
}