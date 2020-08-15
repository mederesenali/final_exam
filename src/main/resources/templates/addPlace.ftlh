
<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/home">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">



            </ul>

        </div>
    </nav>


    <form action="/addPlace" method="post" id="postform" enctype="multipart/form-data">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div class="form-group">
            <label class="flex flex-v-center ">
                <span class="label-input-text">Title:</span>
                <input class="form-control mb-2" type="text" name="tag">
            </label>
        </div>
        <div class="form-group">
            <label for="exampleTextarea">Description</label>
            <textarea class="form-control" id="exampleTextarea" name="description" rows="3"></textarea>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
        </div>
        <div>
            <button class="btn btn-outline-primary btn-block" type="submit">
                Submit new Place!</button>
        </div>

    </form>

</@main.renderWith>