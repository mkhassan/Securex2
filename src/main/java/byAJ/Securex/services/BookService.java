package byAJ.Securex.services;


import byAJ.Securex.models.Book;

public interface BookService {
	
	Iterable <Book> listAllBooks();
	
	Book getBookById(Integer id);
	
	
	Book saveBook(Book book);
	
	void deleteBook(Integer id);

}
