yieldUnescaped "<!DOCTYPE html>"
html(lang:"en") {
    head {
        meta(charset:"utf-8")
        title(title ?: "Ratpack Demo")
        meta('http-equiv': "Content-Type", content:"text/html; charset=utf-8")
        script(src: "/js/jquery-2.1.3.min.js") { }
        script(src: "/js/bootstrap.min.js") { }
        script(src: "/js/jquery.timeago-1.4.1.js") { }
        script("""
            jQuery(document).ready(function() {
                jQuery("abbr.timeago").timeago();
            });
        """)
        link(href: "/css/bootstrap.min.css", rel: "stylesheet")
        link(href: "/css/bootstrap-theme.min.css", rel: "stylesheet")
    }
    body {
        div(class:"container") {
            bodyContents()
        }
    }
}
