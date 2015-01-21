import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        add(MarkupTemplateModule)
    }
}
