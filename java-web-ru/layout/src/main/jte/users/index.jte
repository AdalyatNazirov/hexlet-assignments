@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
title = "User Profile",
body = @`
    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h3>Users List</h3>
            <a href="/users/new" class="btn btn-primary">Add New User</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    @for(var user : page.getUsers())
                        <tr>
                            <td>${user.getId()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                            <td>
                                <a href="/users/${user.getId()}" class="btn btn-sm btn-info">View</a>
                                <a href="/users/${user.getId()}/edit" class="btn btn-sm btn-warning">Edit</a>
                                <a href="/users/${user.getId()}/delete"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                            </td>
                        </tr>
                    @endfor
                    </tbody>
                </table>
            </div>
        </div>
    </div>
`
)