## Ktor - learning samples

1. ktor-ws-chat - [Anton Arhipov repo](https://github.com/antonarhipov/ktor-ws-chat.git) for [Building web applications with Ktor and Exposed // Kotlin by JetBrains//Dec 21, 2021](https://www.youtube.com/watch?v=QE_zk3V0j88)
   
   _ktor-ws-chat взял из репки, а вот ktor-ws-client делал сам, потому что клиента нет в репозитории._

2. ktor-server - примеры с официальной страницы https://ktor.io/docs/welcome.html (см. [ktor-documentation](https://github.com/ktorio/ktor-documentation/tree/2.3.0/codeSnippets/snippets/). Все проекты делаю с помощью [Ktor Project Generator](https://start.ktor.io/)

    2.1 tutorial-server-get-started - [Creating a new Ktor project](https://ktor.io/docs/intellij-idea.html) Plugins : Gradle + Routing plugin

    2.2 tutorial-http-api - [Creating HTTP APIs](https://ktor.io/docs/creating-http-apis.html) Plugins : Routing, ContentNegotiation, kotlinx.serialization
   
    2.3.1 tutorial-website-static - [Creating a static website](https://ktor.io/docs/creating-static-website.html) Plugins : Routing, Static Content
    
    2.3.2 tutorial-website-interactive  [Creating an interactive website](https://ktor.io/docs/creating-interactive-website.html) Used plugins: Routing, FreeMarker	

    2.3.3 tutorial-website-interactive-persistence  [Database persistence with Exposed](https://ktor.io/docs/interactive-website-add-persistence.html) Used plugins: Routing, FreeMarker + Exposed, h2database	

3. Нужно постомтреть более сложные [Samples for Ktor "A collection of ready-to-use samples for Ktor"](https://github.com/ktorio/ktor-samples).

4. Ktor -> war-app : как втянуть в JavaEE приложение?
 
    4.1 ktor-war - сделал через Ktor Project Generator (add plagins: Routing) 

    4.2 ktor-war-big - сделал gradle init 'many modules app'

5. Примеры для сборки Maven 

    5.1 ktor-maven - вроде как пример через генератор Routing+Expose+H2+PostgreSQL и тестовая схема "Hello, Users, Cities"

    5.2 ktor-maven-my - собственная заготовка Netty + Routing, созданный генератором [Ktor Project Generator](https://start.ktor.io/)
        Почти аналог официального [ktor-maven-sample](https://github.com/ktorio/ktor-maven-sample) 



	          




