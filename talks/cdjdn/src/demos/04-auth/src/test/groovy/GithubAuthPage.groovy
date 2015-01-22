import geb.Page

class GithubAuthPage extends Page {
    static at = { title.startsWith("Authorize") }

    static content = {
        authorizeButton { $("button", name:"authorize") }
    }
}
