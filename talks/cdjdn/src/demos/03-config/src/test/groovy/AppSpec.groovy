import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClients
import spock.lang.AutoCleanup
import spock.lang.Specification

class AppSpec extends Specification {
    @AutoCleanup
    def aut = ApplicationUnderTest.of(App)
    def client = TestHttpClients.testHttpClient(aut, { rs -> rs.redirects(10) } )

//    void "different greetings by name and content type"() {
//        when:
//        def response = client.requestSpec({ rs -> rs.headers( { headers -> hreadesr.} ) }) .get("greet/${name}")
//
//        then:
//
//        where:
//        name | contentType |
//    }
}
