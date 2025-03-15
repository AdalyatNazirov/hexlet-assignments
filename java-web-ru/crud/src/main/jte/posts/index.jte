@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <div class="container mt-4">
        <h1>Posts</h1>

        @if(page.getPosts().isEmpty())
            <p>No posts yet</p>
        @else
            <div class="row">
                @for(var post : page.getPosts())
                    <div class="col-12 col-md-6 col-lg-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${post.getName()}</h5>
                                <p class="card-text">${post.getBody()}</p>
                                <a href="${NamedRoutes.postPath(post.getId())}" class="btn btn-primary">View</a>
                            </div>
                        </div>
                    </div>
                @endfor
            </div>

            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item ${page.getPage() == 1 ? "disabled" : ""}">
                        <a class="page-link" href="${NamedRoutes.postsPath(page.getPage() - 1)}">Previous</a>
                    </li>
                    <li class="page-item ${page.getPosts().size() < page.getSize() ? "disabled" : ""}">
                        <a class="page-link" href="${NamedRoutes.postsPath(page.getPage() + 1)}">Next</a>
                    </li>
                </ul>
            </nav>
        @endif
    </div>
`)
