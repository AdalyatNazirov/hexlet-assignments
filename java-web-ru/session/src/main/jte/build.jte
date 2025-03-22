@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    <div class="container mt-5">
        <h2>Вход в систему</h2>

        <!-- Отображение ошибки, если она есть -->
        @if (page.getError() != null)
            <div class="alert alert-danger" role="alert">
                ${page.getError()}
            </div>
        @endif

        <form action="/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Имя пользователя</label>
                <input type="text" class="form-control" id="username" name="name" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Войти</button>
        </form>
    </div>
`)
