<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Home</a>
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
            <h5 class="card-title">Tag:${place.tag}</h5>
            <h6 class="card-title">Title:${place.description}</h6>
                <img src="/img/${place.filename}" class="card-img-top"  >

            <form action="/createReview" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>

                <input type="hidden" name="placeId" value="${place.id}">
                <div class="form-group">
                    <label for="exampleTextarea">Add review:</label>
                    <textarea class="form-control" id="exampleTextarea" name="review" rows="3"></textarea>
                </div>
                <div>
                   Rating: <select class="custom-select mb-3 " name="rating">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <div>
                    <button class="btn btn-outline-primary btn-block" type="submit">Submit review</button>
                </div>
            </form>
        </div>
    </div>



    <div>
        <h1>Reviews</h1>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">author</th>
            <th scope="col">review</th>
            <th scope="col">date</th>
            <th scope="col">rating</th>
        </tr>
        </thead>

        <tbody>
        <#list reviews as c>
            <tr>

                <td>${c.user.email}</td>
                <td>${c.review}</td>
                <td>${c.localDate}</td>
                <td>${c.rating}</td>

            </tr>

        </#list>
        </tbody>

    </table>

<h1>Upload new photo</h1>
    <form action="/addPhoto" method="post" id="postform" enctype="multipart/form-data">
    <div class="form-group custom-file">
        <label class="custom-file-label" for="customFile" >Choose file</label>
        <input type="file" name="file" id="customFile" required>

    </div>
    <div>
        <button class="btn btn-outline-primary btn-block" type="submit">
            Submit new Place!</button>
    </div>

    </form>


</@main.renderWith>