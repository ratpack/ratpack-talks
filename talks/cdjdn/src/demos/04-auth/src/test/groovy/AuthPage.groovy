import geb.Page

class AuthPage extends Page {
    static url = "/auth"

    static at = { title == "Auth" }

    static content = {
        header { $("h1") }
        upLink { $("a", text: "up") }
    }
}
