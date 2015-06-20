import groovy.transform.CompileStatic
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.guice.BindingsSpec
import ratpack.guice.Guice
import ratpack.server.RatpackServer
import ratpack.server.RatpackServerSpec
import ratpack.server.ServerConfig
import ratpack.session.SessionModule

@CompileStatic
class App {
    static void main(String... args) {
        def key = System.getenv("GITHUB_OAUTH_KEY") ?: ''
        def secret = System.getenv("GITHUB_OAUTH_SECRET") ?: ''
        RatpackServer.start({RatpackServerSpec serverSpec ->
            serverSpec
                .serverConfig(ServerConfig.findBaseDir())
                .registry(Guice.registry({ BindingsSpec bindings ->
                    bindings
                        .module(SessionModule)
                        .bindInstance(new GithubSecret(key, secret))
                } as Action))
            .handler(Groovy.Script.handlers())
            } as Action)
    }
}
