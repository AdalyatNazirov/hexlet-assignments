@import exercise.dto.articles.BuildArticlePage

@param BuildArticlePage page


@template.layout.page(
content = @`
    <div class="container mt-5">
        <h1>Create New Article</h1>

        <form action="/articles" method="post">
            <!-- Поле для заголовка -->
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text"
                       class="form-control ${page.getErrors() != null && page.getErrors().containsKey("title") ? "is-invalid" : ""}"
                       id="title"
                       name="title"
                       value="${page.getTitle()}"
                       required>
                @if(page.getErrors() != null && page.getErrors().containsKey("title"))
                    <div class="invalid-feedback">
                        ${page.getErrors().get("title").getFirst().getMessage()}
                    </div>
                @endif
            </div>

            <!-- Поле для содержания статьи -->
            <div class="mb-3">
                <label for="content" class="form-label">Content</label>
                <textarea class="form-control ${page.getErrors() != null && page.getErrors().containsKey("content") ? "is-invalid" : ""}"
                          id="content"
                          name="content"
                          rows="10"
                          required>${page.getContent()}</textarea>
                @if(page.getErrors() != null && page.getErrors().containsKey("content"))
                    <div class="invalid-feedback">
                        ${page.getErrors().get("content").getFirst().getMessage()}
                    </div>
                @endif
            </div>

            <!-- Кнопки -->
            <div class="mb-3">
                <button type="submit" class="btn btn-primary">Create Article</button>
                <a href="/articles" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
`
)
