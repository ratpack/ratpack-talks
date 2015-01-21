import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        get() { render(groovyMarkupTemplate("index.gtpl")) }
        get("salutation/:name") { render("Greetings, ${pathTokens.get("name")}") }
        get("valediction/:name") { render("Farewell, ${pathTokens.get("name")}") }
    }
}
