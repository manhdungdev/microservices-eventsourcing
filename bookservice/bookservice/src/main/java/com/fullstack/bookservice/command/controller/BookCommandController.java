package com.fullstack.bookservice.command.controller;

import com.fullstack.bookservice.command.command.CreateBookCommand;
import com.fullstack.bookservice.command.command.DeleteBookCommand;
import com.fullstack.bookservice.command.command.UpdateBookCommand;
import com.fullstack.bookservice.command.model.BookRequestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    public String addBook(@Valid @RequestBody BookRequestModel model){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(),model.getName(),model.getAuthor(),true);
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{bookId}")
    public String updateBook(@RequestBody BookRequestModel model, @PathVariable String bookId){
        UpdateBookCommand command = new UpdateBookCommand(bookId, model.getName(), model.getAuthor(), model.getIsReady());
        return commandGateway.sendAndWait(command);
    }
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId){
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        return commandGateway.sendAndWait(command);
    }
}