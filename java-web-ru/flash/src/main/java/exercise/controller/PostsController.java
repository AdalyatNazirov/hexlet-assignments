package exercise.controller;

import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.jetbrains.annotations.NotNull;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(@NotNull Context ctx) {

        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(n -> n.length() >= 2, "Name must be at least 2 characters long")
                    .get();
            var body = ctx.formParamAsClass("body", String.class)
                    .get();

            PostRepository.save(new Post(name, body));

            ctx.sessionAttribute("flash", "Post was successfully created!");
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var name = ctx.formParamAsClass("name", String.class)
                    .get();
            var body = ctx.formParamAsClass("body", String.class)
                    .get();

            ctx.render("posts/build.jte", model("page", new BuildPostPage(
                    name,
                    body,
                    e.getErrors()
            )));
        }
    }

    public static void index(@NotNull Context ctx) {
        var posts = PostRepository.getEntities();
        var page = new PostsPage(posts);
        var flash = ctx.<String>consumeSessionAttribute("flash");
        if (flash != null) {
            page.setFlash(flash);
        }

        ctx.render("posts/index.jte", model("page", page));
    }

    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }


}
