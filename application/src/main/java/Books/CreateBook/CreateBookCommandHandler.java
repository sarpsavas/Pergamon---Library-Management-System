package Books.CreateBook;

import interfaces.IRepository;

public class CreateBookCommandHandler {
	
	private IRepository<CreateBookCommand> _repository;
	
	public CreateBookCommandHandler(IRepository<CreateBookCommand> repository)
	{
		_repository = repository;
	}
	
	public void Handle(CreateBookCommand request)
	{
		_repository.Add(request);
	}
}
