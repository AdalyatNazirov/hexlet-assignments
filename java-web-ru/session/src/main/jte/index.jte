@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(content = @`
    <div class="container mt-5">
        <h1>Добро пожаловать на главную страницу</h1>

        @if (page.getName() != null)
            <p>Привет, ${String.valueOf(page.getName())}!</p>
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <button type="submit" class="btn btn-danger">Выйти</button>
            </form>
        @else
            <p><a href="${NamedRoutes.buildSessionPath()}" class="btn btn-primary">Войти</a></p>
        @endif
    </div>
`)
