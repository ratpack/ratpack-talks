import geb.Page

class LogOutPage extends Page {
    static url = "/logout"

    static at = { title == "LoggedOut" }

    static content = {
        header { $("h1") }
        upLink { $("a", text: "up") }
    }
}
