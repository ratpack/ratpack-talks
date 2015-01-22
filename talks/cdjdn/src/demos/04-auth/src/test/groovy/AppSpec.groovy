import geb.spock.GebReportingSpec
import ratpack.test.ApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Shared

class AppSpec extends GebReportingSpec {
    @Shared
    @AutoCleanup
    def aut = ApplicationUnderTest.of(App)

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def cleanup() {
        to LogOutPage
        via GitHubLogOutPage
        if (isAt(GitHubLogOutPage)) {
            signOutButton.click()
        }
    }

    void "noAuth doesn't require authentication"() {
        given:
        to HomePage

        when:
        noAuthLink.click()

        then:
        at NoAuthPage
        header.text() == "NoAuth: Not logged in"
    }

    void "auth requires authentication"() {
        given:
        to HomePage

        when:
        authLink.click()

        then:
        at GithubLoginPage

        when:
        login(username, password)
        if (isAt(GithubAuthPage)) {
            authorizeButton.click()
        }

        then:
        at AuthPage
        header.text() == "Auth: Logged in as davidmc24 (David M. Carr)"
    }

    void "after authentication, noAuth displays logged in information"() {
        given:
        via AuthPage
        at GithubLoginPage
        login(username, password)
        if (isAt(GithubAuthPage)) {
            authorizeButton.click()
        }

        when:
        to NoAuthPage

        then:
        header.text() == "NoAuth: Logged in as davidmc24 (David M. Carr)"
    }

    void "logout terminates session"() {
        given:
        via AuthPage
        at GithubLoginPage
        login(username, password)
        if (isAt(GithubAuthPage)) {
            authorizeButton.click()
        }

        when:
        to LogOutPage

        then:
        header.text().startsWith("Logged out")

        when:
        to NoAuthPage

        then:
        header.text() == "NoAuth: Not logged in"
    }

    String getUsername() {
        System.getenv("GITHUB_USERNAME")
    }

    String getPassword() {
        System.getenv("GITHUB_PASSWORD")
    }
}
