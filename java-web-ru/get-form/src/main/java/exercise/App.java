package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
                    var term = ctx.queryParam("term");

                    var usersStream = USERS.stream();
                    if (!StringUtils.isBlank(term)) {
                        var termUpper = term.toUpperCase();
                        usersStream = usersStream
                                .filter(u -> u.getFirstName().toUpperCase().startsWith(termUpper));
                    }
                    var users = usersStream.toList();

                    var usersPage = new UsersPage(users, term);
                    ctx.render("users/index.jte", model("page", usersPage));
                }
        );
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
