<#-- @ftlvariable name="article" type="ru.unclediga.example.models.Article" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <h3>${article.title}</h3>
    <p>${article.body}</p>
    <p>
        <a href="/articles/${article.id}/edit">Edit article</a>
    </p>
</@layout.header>