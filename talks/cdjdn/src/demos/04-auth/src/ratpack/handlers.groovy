import org.pac4j.oauth.profile.github.GitHubProfile
import ratpack.session.Session

import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        get {
            response.send("text/html", """
            |<html>
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
        get("auth") { GitHubProfile gitHubProfile ->
            response.send("text/html", """
            |<html>
            |<body>
            |<a href="..">Up</a>
            |<h1>Auth: Logged in as <a href="${gitHubProfile.getAttribute("html_url")}">${gitHubProfile.username}</a> (${gitHubProfile.displayName})</h1>
            |<img src="${gitHubProfile.getAttribute("avatar_url")}"/>
            |</body>
            |</html>
            """.stripMargin())
        }
        get("noauth") {
            def gitHubProfileOpt = request.maybeGet(GitHubProfile)
            if (gitHubProfileOpt.isPresent()) {
                def gitHubProfile = gitHubProfileOpt.get()
                response.send("text/html", """
                |<html>
                |<body>
                |<a href="..">Up</a>
                |<h1>NoAuth: Logged in as <a href="${gitHubProfile.getAttribute("html_url")}">${gitHubProfile.username}</a> (${gitHubProfile.displayName})</h1>
                |<img src="${gitHubProfile.getAttribute("avatar_url")}"/>
                |</body>
                |</html>
                """.stripMargin())
            } else {
                response.send("text/html", """
                |<html>
                |<body>
                |<a href="..">Up</a>
                |<h1>NoAuth: Not logged in</h1>
                |</body>
                |</html>
                """.stripMargin())
            }
        }
        get("logout") {
            request.get(Session).terminate()
            response.send("text/html", """
            |<html>
            |<body>
            |<a href="..">Up</a>
            |<h1>Logged out from handlers</h1>
            |</body>
            |</html>
            """.stripMargin())
        }
//        get("logout", new LogoutHandler())
    }
}
