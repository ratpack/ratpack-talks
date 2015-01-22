import geb.Page

class HomePage extends Page {
    static url = "/"

    static at = { title == "Home" }

    static content = {
        authLink { $("a", text: "auth") }
        noAuthLink { $("a", text: "noauth") }
        logoutLink { $("a", text: "logout") }
    }
}
