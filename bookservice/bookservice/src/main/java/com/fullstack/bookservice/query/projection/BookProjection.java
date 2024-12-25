package com.fullstack.bookservice.query.projection;


import com.fullstack.bookservice.command.data.Book;
import com.fullstack.bookservice.command.data.BookRepository;
import com.fullstack.bookservice.query.model.BookResponseModel;
import com.fullstack.bookservice.query.queries.GetAllBookQuery;
import com.fullstack.commonservice.model.BookResponseCommonModel;
import com.fullstack.commonservice.queries.GetBookDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjection {
    private final BookRepository bookRepository;
    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query){
        List<Book> list = bookRepository.findAll();
        return list.stream().map(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book,model);
            return model;
        }).toList();
    }
    @QueryHandler
    public BookResponseCommonModel handle(GetBookDetailQuery query) throws Exception {
        BookResponseCommonModel  bookResponseModel = new BookResponseCommonModel ();
        Book book = bookRepository.findById(query.getId()).orElseThrow(() -> new Exception("Book not found with BookId: "+ query.getId()));
        BeanUtils.copyProperties(book,bookResponseModel);
        return bookResponseModel;
    }
}