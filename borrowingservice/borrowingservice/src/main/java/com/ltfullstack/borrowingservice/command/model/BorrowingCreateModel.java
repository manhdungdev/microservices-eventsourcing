package com.ltfullstack.borrowingservice.command.model;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingCreateModel {
    private String bookId;
    private String employeeId;
}