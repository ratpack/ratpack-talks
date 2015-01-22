import ratpack.groovy.Groovy

import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        get { TwitterService twitterService ->
            twitterService.findTweets("@ratpackweb").map { status ->
                new RatpackTweet(status)
            }.filter { tweet ->
                tweet.score >= 1
            }.toList().subscribe { tweets ->
                render Groovy.groovyMarkupTemplate("index.gtpl", tweets:tweets)
            }
        }
        assets("assets")
    }
}
