import ratpack.groovy.Groovy
import ratpack.server.RatpackServer
import ratpack.server.ServerConfig

class App {
    static void main(String... args) {
        def server = RatpackServer.of { serverSpec ->
            serverSpec
                    .config(ServerConfig.findBaseDirProps())
                    .registry(Groovy.Script.bindings())
                    .handler(Groovy.Script.handlers())
        }
        server.start()
    }
}
