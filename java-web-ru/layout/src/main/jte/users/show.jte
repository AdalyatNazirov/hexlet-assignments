@import exercise.dto.users.UserPage

@param UserPage page

@template.layout.page(
title = "User Profile",
body = @`
    <div class="card">
        <div class="card-header">
            <h3>User Profile</h3>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-8">
                    <h4>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h4>
                    <p class="text-muted">${page.getUser().getEmail()}</p>
                    <div class="mt-3">
                        <p><strong>ID:</strong> ${page.getUser().getId()}</p>
                        <p><strong>First Name:</strong> ${page.getUser().getFirstName()}</p>
                        <p><strong>Last Name:</strong> ${page.getUser().getLastName()}</p>
                        <p><strong>Email:</strong> ${page.getUser().getEmail()}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
`
)