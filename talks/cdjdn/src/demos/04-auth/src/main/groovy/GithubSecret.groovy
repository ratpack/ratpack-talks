import groovy.transform.CompileStatic

@CompileStatic
class GithubSecret {
    final String key
    final String secret
    GithubSecret(String key, String secret) {
        this.key = key
        this.secret = secret
    }
}
