package com.LibraryManager.Library.payload;

import lombok.Data;

@Data
public class BookDto {
    private long id;
    private String title;
    private double price;
    private long authorId;
}
