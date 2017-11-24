package lab.presentation;

import lab.application.AppConfig;
import lab.application.ApplicationService;
import lab.application.impl.ApplicationServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationService bookserv = (ApplicationService)applicationContext.getBean(ApplicationServiceImpl.class);
		//DataSource data = (DataSource)applicationContext.getBean(DataSource.class);

		bookserv.createRandomBooks(5);

		/*Book book = new Book();
		book.setId(1);
		book.setName("nam");
		book.setAuthor("a");
		bookRep.createBook(book);
		Book book2 = bookRep.getBooks().get(0);
		System.out.println(book2);*/
	}
}
