<#import "partials/main.ftlh" as main />

<@main.renderWith>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/home">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">




        </ul>

    </div>
</nav>
<div class="card" style="width: 18rem;">
    <div class="card-body">
        <h5 class="card-title">Name:${user.fullName}</h5>
        <h6 class="card-subtitle mb-2 text-muted">Email:${user.email}</h6>

    </div>
</div>

</@main.renderWith>