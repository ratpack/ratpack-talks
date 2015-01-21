import ratpack.jackson.Jackson

import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        get("greet/:name") { AppConfig config ->
            def name = pathTokens.get("name")
            byContent {
                json {
                    render Jackson.json([greeting:config.greeting, name:name])
                }
                plainText {
                    render "${config.greeting} ${name}"
                }
                html {
                    render "${config.greeting} <b>${name}</b>"
                }
            }
        }
    }
}
