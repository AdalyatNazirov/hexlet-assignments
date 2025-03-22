package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.jetbrains.annotations.NotNull;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {


    public static void build(@NotNull Context ctx) {
        ctx.render("build.jte", model("page", new LoginPage()));
    }

    public static void create(@NotNull Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        try {
            var user = UsersRepository.findByName(name)
                    .orElseThrow(() -> new NotFoundResponse("Wrong username or password"));
            var password = ctx.formParamAsClass("password", String.class)
                    .get();

            if (!user.getPassword().equals(Security.encrypt(password))) {
                throw new NotFoundResponse("Wrong username or password");
            }

            ctx.sessionAttribute("name", name);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (NotFoundResponse e) {
            ctx.render("build.jte", model("page", new LoginPage(name, e.getMessage())));
        }
    }

    public static void destroy(@NotNull Context ctx) {
        ctx.sessionAttribute("name", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void show(@NotNull Context ctx) {
        var name = ctx.sessionAttribute("name");
        ctx.render("index.jte", model("page", new MainPage(name)));
    }
}
