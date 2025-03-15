package exercise.controller;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.repository.PostRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var pageNum = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var size = ctx.queryParamAsClass("size", Integer.class).getOrDefault(5);

        var posts = PostRepository.findAll(pageNum, size);
        var page = new PostsPage(pageNum, size, posts);
        ctx.render("posts/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
    // END
}
