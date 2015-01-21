import ratpack.jackson.JacksonModule

import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        add(JacksonModule)
    }
}
