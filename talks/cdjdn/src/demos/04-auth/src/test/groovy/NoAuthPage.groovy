import geb.Page

class NoAuthPage extends Page {
    static url = "/noauth"

    static at = { title == "NoAuth" }

    static content = {
        header { $("h1") }
        upLink { $("a", text: "up") }
    }
}
