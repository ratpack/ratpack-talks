import ratpack.http.internal.HttpHeaderConstants
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClients
import spock.lang.AutoCleanup
import spock.lang.Specification

class AppSpec extends Specification {
    @AutoCleanup
    def aut = ApplicationUnderTest.of(App)
    def client = TestHttpClients.testHttpClient(aut, { rs -> rs.redirects(10) } )

    void "noAuth doesn't require authentication"() {
        expect:
        client.getText("noauth").contains("NoAuth: Not logged in")
    }

    void "auth requires authentication"() {
        when:
        def response = client.requestSpec({ rs -> rs.redirects(0) }).get("auth")

        then:
        println response.statusCode
        println response.headers.get(HttpHeaderConstants.LOCATION)
        println response.headers.asMultiValueMap()
        println client.cookies
        response.statusCode == 302
        response.status.message == "Found"
        response.headers.get(HttpHeaderConstants.LOCATION).startsWith("https://github.com/login/oauth/authorize")
    }

    void "can authenticate"() {
        when:
        def response = client.get("auth")

        then:
        println response.statusCode
        println response.headers.get(HttpHeaderConstants.LOCATION)
        println response.headers.asMultiValueMap()
        println client.cookies
        response.statusCode == 200
    }

    void "after authentication, noAuth displays logged in information"() {

    }

    void "logout terminates session"() {

    }
}
