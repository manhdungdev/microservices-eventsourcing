package com.ltfullstack.borrowingservice.command.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDeletedEvent {
    String id;
}