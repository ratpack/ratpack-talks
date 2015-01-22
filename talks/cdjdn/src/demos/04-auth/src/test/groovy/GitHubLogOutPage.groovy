import geb.Page

class GitHubLogOutPage extends Page {
    static url = "https://github.com/logout"

    static at = { title == "Sign out?" }

    static content = {
        signOutButton { $("input", type:"submit") }
    }
}
