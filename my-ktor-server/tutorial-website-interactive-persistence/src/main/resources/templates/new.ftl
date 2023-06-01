<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Create article</h3>
        <form method="post" action="/articles">
            <p><input type="text" name="title"></p>
            <p><textarea name="body"></textarea></p>
            <p><input type="submit"/></p>
        </form>
    </div>
</@layout.header>


