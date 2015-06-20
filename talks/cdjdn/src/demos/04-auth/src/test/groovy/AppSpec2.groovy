import ratpack.http.internal.HttpHeaderConstants
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Specification

/**
 * Created by danny on 2015-06-20.
 */
class AppSpec2 extends Specification {

    @AutoCleanup
    def aut = ApplicationUnderTest.of(App)

    @Delegate
    TestHttpClient client = TestHttpClient.testHttpClient(aut) { s -> s.redirects(10) }

    void "noAuth doesn't require authentication"() {
        expect:
        getText("noauth").contains("NoAuth: Not logged in")
    }

    void "auth requires authentication"() {
        when:
        def response = requestSpec({ rs -> rs.redirects(0) }).get("auth")

        then:
        println response.statusCode
        println response.headers.get(HttpHeaderConstants.LOCATION)
        println response.headers.asMultiValueMap()
        println cookies
        response.statusCode == 302
        response.status.message == "Found"
        response.headers.get(HttpHeaderConstants.LOCATION).startsWith("https://github.com/login/oauth/authorize")
    }

    void "can authenticate"() {
        when:
        def response = get("auth")

        then:
        println response.statusCode
        println response.headers.get(HttpHeaderConstants.LOCATION)
        println response.headers.asMultiValueMap()
        println cookies
        response.statusCode == 200
    }

    void "after authentication, noAuth displays logged in information"() {

    }

    void "logout terminates session"() {

    }
}
