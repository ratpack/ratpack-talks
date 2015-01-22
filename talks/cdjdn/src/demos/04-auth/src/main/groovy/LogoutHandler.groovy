import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.session.Session

class LogoutHandler extends GroovyHandler {
    @Override
    protected void handle(GroovyContext context) {
        context.request.get(Session).terminate()
        context.response.send("text/html", """
        |<html>
        |<body>
        |<a href="..">Up</a>
        |<h1>Logged out from handler class</h1>
        |</body>
        |</html>
        """.stripMargin())
    }
}
