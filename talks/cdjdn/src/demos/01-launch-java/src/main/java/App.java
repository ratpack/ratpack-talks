import ratpack.server.RatpackServer;

public class App {
    public static void main(String... args) throws Exception {
        RatpackServer.start(serverSpec -> serverSpec
                .handlers(chain -> chain
                        .get("salutation/:name", ctx -> ctx.render("Greetings, " + ctx.getPathTokens().get("name")))
                        .get("valediction/:name", ctx -> ctx.render("Farewell, " + ctx.getPathTokens().get("name")))));
    }
}
