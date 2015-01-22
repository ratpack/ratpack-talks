import ratpack.groovy.template.MarkupTemplateModule
import twitter4j.Twitter
import twitter4j.TwitterFactory

import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        bindInstance(Twitter, TwitterFactory.singleton)
        bind(TwitterService)
        add(MarkupTemplateModule)
    }
}
