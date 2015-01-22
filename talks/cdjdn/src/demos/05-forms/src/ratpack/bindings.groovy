import groovy.sql.Sql
import ratpack.groovy.sql.SqlModule
import ratpack.h2.H2Module
import ratpack.jackson.JacksonModule
import ratpack.server.ServerLifecycleListener
import ratpack.server.StartEvent
import ratpack.thymeleaf.ThymeleafModule

import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        add(ThymeleafModule)
        add(H2Module)
        add(JacksonModule)
        add(SqlModule)
        bindInstance(ServerLifecycleListener, new ServerLifecycleListener() {
            @Override
            void onStart(StartEvent event) throws Exception {
                def sql = event.registry.get(Sql)
                sql.executeUpdate("CREATE TABLE person (id BIGINT IDENTITY, NAME VARCHAR(255), ADDRESS VARCHAR(255));")
            }
        })
    }
}
