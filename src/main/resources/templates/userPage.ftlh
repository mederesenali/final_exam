<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Cafe Critic</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <ul class="nav navbar-nav ml-auto w-100 justify-content-end">
            <li class="nav-item">
              <a class="nav-link" href="/profile"> hello, ${user.fullName}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/addPlace">Add new place</a>

            </li>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Выйти</button>
            </form>

        </ul>
    </nav>



</@main.renderWith>