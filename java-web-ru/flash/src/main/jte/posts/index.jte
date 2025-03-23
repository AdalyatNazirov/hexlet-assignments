@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <div class="container mt-4">
        <h1 class="mb-4">Posts</h1>
        <div class="mb-3">
            <a href="${NamedRoutes.buildPostPath()}" class="btn btn-primary">Create New Post</a>
        </div>
        <div class="list-group">
            @for(var post: page.getPosts())
                <a href="${NamedRoutes.postPath(post.getId())}" class="list-group-item list-group-item-action">
                    <h5 class="mb-1">${post.getName()}</h5>
                </a>
            @endfor
        </div>
    </div>
`,
)
