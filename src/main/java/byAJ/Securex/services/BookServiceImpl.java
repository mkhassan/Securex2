package byAJ.Securex.services;


import byAJ.Securex.models.Book;
import byAJ.Securex.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class BookServiceImpl implements BookService {
	
	
	private BookRepository bookRepository;
	
	@Autowired 
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Iterable<Book> listAllBooks() {

		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(Integer id) {
		
		return bookRepository.findOne(id);
	}

	@Override
	public Book saveBook(Book book) {
		
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Integer id) {
		bookRepository.delete(id);
		
	}
	
	
	
	
	

}
