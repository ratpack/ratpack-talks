import twitter4j.Status

import java.time.OffsetDateTime
import java.time.ZoneOffset

class RatpackTweet {
    OffsetDateTime dateTime
    String from
    String content
    String fromUrl
    String profileImageUrl
    int favoriteCount
    int retweetCount

    RatpackTweet(Status status) {
        this.dateTime = status.createdAt.toInstant().atOffset(ZoneOffset.UTC)
        this.from = "@${status.user.screenName}"
        this.content = status.text
        this.fromUrl = status.user.URL
        this.profileImageUrl = status.user.profileImageURL
        this.favoriteCount = status.favoriteCount
        this.retweetCount = status.retweetCount
    }

    int getScore() {
        favoriteCount + retweetCount
    }
}
