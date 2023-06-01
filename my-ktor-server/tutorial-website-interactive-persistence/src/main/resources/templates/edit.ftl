<#-- @ftlvariable name="article" type="ru.unclediga.example.models.Article" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Edit article</h3>
        <form action="/articles/${article.id}" method="post">
            <label>Title:
                <input type="text" name="title" value="${article.title}"/>
            </label>
            <p/>
            <label title="Body">Body:
                <textarea name="body" >${article.body}</textarea>
            </label>
            <p/>
            <input type="submit" name="_action" value="update">
        </form>
    </div>

    <div>
        <form action="/articles/${article.id}" method="post">
            <input type="submit" name="_action" value="delete">
        </form>
    </div>
</@layout.header>