import ratpack.http.internal.HttpHeaderConstants
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClients
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Unroll

class AppSpec extends Specification {
    @AutoCleanup
    def aut = ApplicationUnderTest.of(App)
    def client = TestHttpClients.testHttpClient(aut)

    @Unroll
    void "different greetings by name and content type: #name; #contentType"() {
        when:
        def response = client.requestSpec({ rs ->
            rs.headers({ headers ->
                headers.set(HttpHeaderConstants.ACCEPT, contentType)
            })
        }).get("greet/${name}")

        then:
        response.body.contentType.type == contentType
        response.body.text == expectedText

        where:
        name | contentType | expectedText
        "Leonard" | "text/plain" | "Greetings, Leonard"
        "Sheldon" | "text/plain" | "Greetings, Sheldon"
        "Howard" | "text/html" | "Greetings, <b>Howard</b>"
        "Rajesh" | "text/html" | "Greetings, <b>Rajesh</b>"
        "Penny" | "application/json" | "{\n  \"greeting\" : \"Greetings,\",\n  \"name\" : \"Penny\"\n}"
    }
}
