
<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h1 class="center">Sign In</h1>
    <form class="mt-5" action="/login" method="post">
        <#if error!false >
            <p style="color:red">Неверные имя пользователя или пароль !</p>
        </#if>
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" name="username" aria-describedby="emailHelp" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="/registration" class="btn btn-primary">registration</a>
    </form>
</@main.renderWith>