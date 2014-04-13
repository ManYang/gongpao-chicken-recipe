package recipe.alpha.google;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings("serial")

public class KongbaoChickenServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException
			{
			KongbaoChicken.insertSimpleTextTimelineItem(req);
			resp.setContentType("text/plain");
			resp.getWriter().append( "Inserted Timeline Item" );
			resp.setContentType("text/html; charset=utf-8");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("food", KongbaoChickenServlet.getRandomCuisine());
			String html = KongbaoChickenServlet.render(
			getServletContext(), "web/cuisine.ftl", data);
			resp.getWriter().append(html);
			}

	public static String getRandomCuisine()
	{
	String[] lunchOptions = {
	"American", "Chinese", "French", "Italian", "Japenese", "Thai"
	};
	int choice = new Random().nextInt(lunchOptions.length);
	return lunchOptions[choice];
	}
	
	public static String render(ServletContext ctx, String template,
			Map<String, Object> data)
			throws IOException, ServletException
			{
			Configuration config = new Configuration();
			config.setServletContextForTemplateLoading(ctx, "WEB-INF/views");
			config.setDefaultEncoding("UTF-8");
			Template ftl = config.getTemplate(template);
			try {
			// use the data to render the template to the servlet output
			StringWriter writer = new StringWriter();
			ftl.process(data, writer);
			return writer.toString();
			}
			catch (TemplateException e) {
			throw new ServletException("Problem while processing template", e);
			}
			}
}

