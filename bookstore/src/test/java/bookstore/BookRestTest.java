package bookstore;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fortech.bookstore.configuration.JAXRSConfiguration;
import com.fortech.bookstore.model.Book;
import com.fortech.bookstore.model.Language;
import com.fortech.bookstore.repository.BookService;
import com.fortech.bookstore.rest.BookEndpoint;
import com.fortech.bookstore.util.NumberGenerator;
import com.fortech.bookstore.util.TextUtil;
import com.fortech.bookstore.util.annotation.generator.IsbnGenerator;

@RunWith(Arquillian.class)
@RunAsClient
public class BookRestTest {

	  private static Logger logger = Logger.getLogger(BookRestTest.class);
	
	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(BookService.class)
				.addClass(Book.class)
				.addClass(Language.class)
				.addClass(TextUtil.class)
				.addClass(NumberGenerator.class)
				.addClass(IsbnGenerator.class)
				.addClass(BookEndpoint.class)
				.addClass(JAXRSConfiguration.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
	}
	
	@Test
	public void testCreateRest(@ArquillianResteasyResource("api/books") WebTarget webTarget) {
		Book book = new Book("Javaa    Book", "bla  bla", 12.5f, "isbn", new Date(), 150, "image", Language.ENGLISH);
		logger.info(webTarget.getUri().toString());
		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(book));
		
		assertEquals(response.getStatus(),Status.CREATED.getStatusCode());
	}

}
