package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.Security;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        // BEGIN

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            var email = Objects.requireNonNull(ctx.formParam("email")).trim().toLowerCase();
            var firstName = StringUtils.capitalize(Objects.requireNonNull(ctx.formParam("firstName")).trim());
            var lastName = StringUtils.capitalize(Objects.requireNonNull(ctx.formParam("lastName")).trim());
            var passwordHash = Security.encrypt(Objects.requireNonNull(ctx.formParam("password")).trim());

            UserRepository.save(new User(firstName, lastName, email, passwordHash));

            ctx.redirect("/users");
        });

        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
