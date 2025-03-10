@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
content = @`
    <div class="container mt-4">
        <!-- Search Form -->
        <div class="row mb-4">
            <div class="col-md-6">
                <form method="get" class="d-flex">
                    <input type="text" name="term" class="form-control me-2" placeholder="Search users..."
                           value="${page.getTerm()}">
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        </div>

        <!-- Users List -->
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h4>Users List</h4>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Assuming you're using a template engine like Thymeleaf -->
                            <!-- Replace this with your actual template syntax -->
                            @for(var user : page.getUsers())
                                <tr>
                                    <td>${user.getId()}</td>
                                    <td>${user.getFirstName()}</td>
                                    <td>${user.getLastName()}</td>
                                    <td>${user.getEmail()}</td>
                                </tr>
                            @endfor
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
`
)
