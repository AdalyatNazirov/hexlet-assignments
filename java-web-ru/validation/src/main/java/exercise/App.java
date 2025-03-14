package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;

import java.util.List;

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

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/articles/build", ctx -> {
            var page = new BuildArticlePage();

            ctx.render("articles/build.jte", model("page", page));
        });

        app.post("/articles", ctx -> {

            try {
                var title = ctx.formParamAsClass("title", String.class)
                        .check(t -> t.length() >= 2, "Название не должно быть короче двух символов")
                        .check(t -> ArticleRepository.findByTitle(t).isEmpty(), "Статья должна быть не короче 10 символов")
                        .get();

                var body = ctx
                        .formParamAsClass("body", String.class).check(t -> t.length() >= 10, "Статья с таким названием уже существует")
                        .get();

                ArticleRepository.save(new Article(title, body));

                ctx.redirect("/articles");
            } catch (ValidationException validationException) {
                var page = new BuildArticlePage(ctx.formParamAsClass("title", String.class).get(), ctx.formParamAsClass("body", String.class).get(), validationException.getErrors());
                ctx.render("articles/build.jte", model("page", page));
            }

        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
