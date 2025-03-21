package exercise.controller;

import exercise.dto.users.UserPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN

    public static void show(Context ctx) throws Exception {
        var id = ctx.pathParamAsClass("id", Long.class)
                .check(v -> v > 0, "Неверно указано значение")
                .get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse(""));

        if (!Objects.equals(user.getToken(), ctx.cookie("token"))) {
            ctx.status(401).result("Not authorized");
            return;
        }

        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws Exception {
        var firstName = ctx.formParamAsClass("firstName", String.class).get();
        var lastName = ctx.formParamAsClass("lastName", String.class).get();
        var email = ctx.formParamAsClass("email", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();

        var user = new User(
                firstName,
                lastName,
                email,
                Security.encrypt(password),
                Security.generateToken()
        );
        UserRepository.save(user);
        ctx.cookie("token", user.getToken());
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }
    // END
}
