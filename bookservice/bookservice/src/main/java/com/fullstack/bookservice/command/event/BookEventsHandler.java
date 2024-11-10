package com.fullstack.bookservice.command.event;

import com.fullstack.bookservice.command.data.Book;
import com.fullstack.bookservice.command.data.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookEventsHandler {
    private final BookRepository bookRepository;
    @EventHandler
    public void on(BookCreatedEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event){
        bookRepository.findById(event.getId()).ifPresent(book -> {
            book.setName(event.getName());
            book.setAuthor(event.getAuthor());
            book.setIsReady(event.getIsReady());
            bookRepository.save(book);
        });
    }
    @EventHandler
    public void on(BookDeletedEvent event){
        bookRepository.findById(event.getId()).ifPresent(book -> bookRepository.delete(book));
    }
}