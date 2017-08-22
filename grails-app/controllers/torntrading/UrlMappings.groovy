package torntrading

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/AD2F6917563C3933684C2D3AD746575C.txt"(view:"/certVerification")
        "/test.txt"(view:"/textFile")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/appInfo"(view:'/appInfo')
    }
}
