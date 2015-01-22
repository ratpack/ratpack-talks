import geb.Page

class GithubLoginPage extends Page {
    static at = { title == "Sign in · GitHub" }

    static content = {
        usernameField { $("#login_field") }
        passwordField { $("#password") }
        signInButton { $("input", name:"commit") }
    }

    void login(String username, String password) {
        usernameField.value(username)
        passwordField.value(password)
        signInButton.click()
    }
}
