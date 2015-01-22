import org.pac4j.core.profile.UserProfile
import org.pac4j.oauth.client.GitHubClient
import ratpack.groovy.Groovy
import ratpack.guice.Guice
import ratpack.handling.Context
import ratpack.pac4j.Authorizer
import ratpack.pac4j.Pac4jModule
import ratpack.server.RatpackServer
import ratpack.server.ServerConfig
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule

class App {
    static void main(String... args) {
        def key = System.getenv("GITHUB_OAUTH_KEY")
        def secret = System.getenv("GITHUB_OAUTH_SECRET")
        RatpackServer.start { serverSpec ->
            def authorizer = [
                    isAuthenticationRequired: { Context context ->
                        context.request.path.startsWith("auth")
                    },
                    handleAuthorization: { Context context, UserProfile userProfile ->
                        context.next()
                    }
            ] as Authorizer
            serverSpec
                    .config(ServerConfig.findBaseDirProps())
                    .registry(Guice.registry { bindings ->
                        bindings.add(SessionModule)
                        bindings.add(new MapSessionsModule(10, 60))
                        bindings.add(new Pac4jModule(new GitHubClient(key:key, secret:secret, scope:"user:email"), authorizer))
                    })
                    .handler(Groovy.Script.handlers())
        }
    }
}
