import rx.Observable
import twitter4j.Query
import twitter4j.QueryResult
import twitter4j.Status
import twitter4j.Twitter

import javax.inject.Inject

class TwitterService {
    @Inject
    Twitter twitter

    Observable<Status> findTweets(String queryStr) {
        findResults(new Query(queryStr)).flatMap { queryResult ->
            Observable.from(queryResult.tweets)
        }
    }

    private Observable<QueryResult> findResults(Query query) {
        def result = twitter.search(query)
        def obs = Observable.just(result)
        if (result.hasNext()) {
            obs = obs.concatWith(findResults(result.nextQuery()))
        }
        obs
    }
}
