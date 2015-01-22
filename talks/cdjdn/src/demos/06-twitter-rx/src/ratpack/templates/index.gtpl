import java.time.format.DateTimeFormatter

layout "layout.gtpl",
title: "Recent Top Ratpack Tweets",
bodyContents: contents {
    div(class:"panel panel-primary") {
        div(class:"panel-heading") {
            h1(class:"panel-title", "Recent Top Ratpack Tweets")
        }
        ul(class:"list-group") {
            tweets.each { RatpackTweet tweet ->
                li(class:"list-group-item") {
                    img(src:tweet.profileImageUrl, width:"32px", height:"32px", class:"pull-left")
                    span(class:"pull-right") {
                        i(class:"glyphicon glyphicon-star", "aria-hidden":"true") { }
                        yield " ${tweet.favoriteCount}"
                    }
                    abbr(title:tweet.dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), class:"timeago pull-right") { }
                    span(class:"col-md-2") { a(href:tweet.fromUrl, tweet.from) }
                    yield " "
                    span(style:"overflow: hidden; text-overflow: ellipsis; white-space: nowrap; display: inline-block; max-width: 700px;", tweet.content)
                }
            }
        }
    }
}
