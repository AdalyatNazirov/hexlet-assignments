@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
content = @`
    <div class="container mt-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${NamedRoutes.postsPath()}">Posts</a></li>
                <li class="breadcrumb-item active" aria-current="page">${page.getPost().getName()}</li>
            </ol>
        </nav>

        <div class="row">
            <div class="col-md-8 mx-auto">
                <div class="card">
                    <div class="card-header">
                        <h2>${page.getPost().getName()}</h2>
                    </div>
                    <div class="card-body">
                        <p class="card-text">${page.getPost().getBody()}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
`)