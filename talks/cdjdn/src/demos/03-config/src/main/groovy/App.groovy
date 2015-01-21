import ratpack.config.Configurations
import ratpack.groovy.Groovy
import ratpack.registry.Registries
import ratpack.registry.Registry
import ratpack.server.RatpackServer
import ratpack.server.ServerConfig

class App {
    static void main(String... args) {
        RatpackServer.start { serverSpec ->
            def configData = Configurations.config().props(["server.baseDirProps":""]).yaml("config.yaml").env().sysProps().build()
            serverSpec
                    .config(configData.get("/server", ServerConfig))
                    .registry(Groovy.Script.bindings().andThen { Registry registry ->
                        registry.join(Registries.registry { registrySpec ->
                            registrySpec.add(configData.get("/app", AppConfig))
                        })
                    })
                    .handler(Groovy.Script.handlers())
        }
    }
}
