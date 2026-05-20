package com.pergamon.application.books.upload_book_image;

import org.springframework.stereotype.Component;

import com.pergamon.core.interfaces.IBookRepository;
import com.pergamon.core.interfaces.IUploadBookImageService;

@Component
public class UploadBookImageServiceImpl implements IUploadBookImageService {
	
	private IBookRepository _bookRepository;
	
	public void addBookImage(String bookId, String url, String organizationPerId)
	{
		_bookRepository.addImage(bookId, organizationPerId, url);
	}
}
