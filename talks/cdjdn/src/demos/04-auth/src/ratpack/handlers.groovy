import org.pac4j.oauth.client.GitHubClient
import org.pac4j.oauth.profile.github.GitHubProfile
import ratpack.pac4j.RatpackPac4j

import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers { GithubSecret githubSecret ->
        all(RatpackPac4j
                .authenticator(
                new GitHubClient(key:githubSecret.key, secret:githubSecret.secret, scope:"user:email")))
        get {
            response.send("text/html", """
            |<html>
            |<head><title>Home</title></head>
            |<body>
            |<ul>
            |<li><a href="auth">auth</a>
            |<li><a href="noauth">noauth</a>
            |<li><a href="logout">logout</a>
            |</ul>
            |</body>
            |</html>
            """.stripMargin())
        }
        prefix("auth") {
            all(RatpackPac4j.requireAuth(GitHubClient))
            get { GitHubProfile gitHubProfile ->
                response.send("text/html", """
                |<html>
                |<head><title>Auth</title></head>
                |<body>
                |<ul>
                |<li><a href="auth">auth</a>
                |<li><a href="noauth">noauth</a>
                |<li><a href="logout">logout</a>
                |</ul>
                |<h1>auth</h1>
                |<h1>Auth: Logged in as <a href="${gitHubProfile.getAttribute("html_url")}">${gitHubProfile.username}</a> (${gitHubProfile.displayName})</h1>
                |<img src="${gitHubProfile.getAttribute("avatar_url")}"/>
                |</body>
                |</html>
                """.stripMargin())
            }
        }
        get("noauth") {
            RatpackPac4j.userProfile(it, GitHubProfile)
                .then { gitHubProfileOpt ->
                    if (gitHubProfileOpt.isPresent()) {
                        def gitHubProfile = gitHubProfileOpt.get()
                        response.send("text/html", """
                    |<html>
                    |<head><title>NoAuth</title></head>
                    |<body>
                    |<ul>
                    |<li><a href="auth">auth</a>
                    |<li><a href="noauth">noauth</a>
                    |<li><a href="logout">logout</a>
                    |</ul>
                    |<h1>noauth</h1>
                    |<h1>NoAuth: Logged in as <a href="${gitHubProfile.getAttribute("html_url")}">${gitHubProfile.username}</a> (${gitHubProfile.displayName})</h1>
                    |<img src="${gitHubProfile.getAttribute("avatar_url")}"/>
                    |</body>
                    |</html>
                    """.stripMargin())
                    } else {
                        response.send("text/html", """
                    |<html>
                    |<head><title>NoAuth</title></head>
                    |<body>
                    |<ul>
                    |<li><a href="auth">auth</a>
                    |<li><a href="noauth">noauth</a>
                    |<li><a href="logout">logout</a>
                    |</ul>
                    |<h1>noauth</h1>
                    |<h1>NoAuth: Not logged in</h1>
                    |</body>
                    |</html>
                    """.stripMargin())
                    }
                }
        }
        get("logout") {
            RatpackPac4j.logout(it).then {
                response.send("text/html", """
                |<html>
                |<head><title>LoggedOut</title></head>
                |<body>
                |<ul>
                |<li><a href="auth">auth</a>
                |<li><a href="noauth">noauth</a>
                |<li><a href="logout">logout</a>
                |</ul>
                |<h1>logout</h1>
                |<h1>Logged out from handlers</h1>
                |</body>
                |</html>
                """.stripMargin())
            }
        }
//        get("logout", new LogoutHandler())
    }
}
