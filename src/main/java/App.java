import java.util.HashMap;
import spark.ModelAndView;
import java.util.ArrayList;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("categoryList", Category.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String userCategory = request.queryParams("name");
      Category newCategory = new Category(userCategory);
      newCategory.save();
      model.put("categoryList", Category.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
 }
}
