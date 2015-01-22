import groovy.sql.Sql
import ratpack.form.Form
import ratpack.http.internal.HttpHeaderConstants
import ratpack.jackson.Jackson
import ratpack.thymeleaf.Template

import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        handler(":id") { Sql sql ->
            def id = pathTokens.asLong("id")
            byMethod {
                get {
                    blocking {
                        sql.firstRow("SELECT name, address FROM person WHERE id = :id", id: id)
                    }.then { row ->
                        if (row) {
                            def person = new Person(id: id, name: row.name, address: row.address)
                            byContent {
                                html {
                                    render "<p>${person.name}</p><p>${person.address}</p>"
                                }
                                plainText {
                                    render "${person.name} lives at ${person.address}"
                                }
                                json {
                                    render Jackson.json(person)
                                }
                            }
                        } else {
                            response.status(404).send()
                        }
                    }
                }
                put {
                    // Update only, no insert
                    def person = parse(Jackson.fromJson(Person))
                    blocking {
                        sql.executeUpdate("UPDATE person SET name=:name, address=:address WHERE id=:id", id: id, name: person.name, address: person.address)
                    }.then { rowsChanged ->
                        if (rowsChanged) {
                            response.status(200).send()
                        } else {
                            response.status(404).send()
                        }
                    }
                }
                delete {
                    blocking {
                        sql.executeUpdate("DELETE person WHERE id=:id", id: id)
                    }.then { rowsChanged ->
                        if (rowsChanged) {
                            response.status(200).send()
                        } else {
                            response.status(404).send()
                        }
                    }
                }
            }
        }
        handler { Sql sql ->
            byMethod {
                get {
                    blocking {
                        def people = []
                        sql.eachRow("SELECT id, name, address FROM person") {
                            people << new Person(id:it.id, name:it.name, address:it.address)
                        }
                        people
                    }.then { people ->
                        byContent {
                            html {
                                render(Template.thymeleafTemplate("index", people: people))
                            }
                            json {
                                render(Jackson.json(people))
                            }
                        }
                    }
                }
                post {
                    def rest
                    def person
                    if (request.body.contentType.json) {
                        person = parse(Jackson.fromJson(Person))
                        rest = true
                    } else if (request.body.contentType.form) {
                        def form = parse(Form)
                        person = new Person(name:form.name, address:form.address)
                        rest = false
                    }
                    blocking {
                        sql.executeInsert("INSERT INTO person (name, address) VALUES (:name, :address)", name: person.name, address: person.address)
                    }.then { result ->
                        def id = result[0][0]
                        if (rest) {
                            response.headers.set(HttpHeaderConstants.LOCATION, "/${id}")
                            response.status(201).send()
                        } else {
                            redirect("/")
                        }
                    }
                }
            }
        }
    }
}
