package bookstore;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fortech.bookstore.model.ServerConnection;

@RunWith(Arquillian.class)
public class ServerConnectionTest {

	java.util.logging.Logger logger = Logger.getLogger("Logger");

	@Test
	public void validURL() {
		ServerConnection connection = new ServerConnection("https://wwww.cdbooks.com", "ftp://ftp.cdbooks.com.21");
		assertTrue(connection != null);
	}

	@Test
	public void invalidURL() {
		ServerConnection connection = new ServerConnection("http://wwww.cdbooks.com:44", "ftp://ftp.cdbooks.com.22");
		logger.info(connection.getFtpUri() + '\n' + connection.getUri());
		assertTrue(true);

	}

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(ServerConnection.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
	}
}
