import ratpack.groovy.Groovy
import ratpack.server.RatpackServer
import ratpack.server.ServerConfig

class App {
    static void main(String... args) {
        RatpackServer.start { serverSpec ->
            // TODO: remove flags when next snapshot builds
            serverSpec
                    .config(ServerConfig.findBaseDirProps())
                    .registry(Groovy.Script.bindings(false))
                    .handler(Groovy.Script.handlers(false))
        }
    }
}
