/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.okta.developer.presentation;

import com.okta.developer.application.BookService;
import com.okta.developer.entities.Book;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BookBean {

    @Inject
    private BookService bookService;
    private String bookTitle;
    private Book selectedBook;

    private Integer bookId;
    private Book book;
    private String bookAuthor;
    private double price;
    private int quantity;

    @PostConstruct
    public void postConstruct() {
        String bookIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookId");
        if (bookIdParam != null) {
            bookId = Integer.parseInt(bookIdParam);
            book = bookService.getBook(bookId);
            bookTitle = book.getBookTitle();
            bookAuthor = book.getBookAuthor();
            price = book.getPrice();
            quantity = book.getQuantity();
        }

    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String add() {
        Book book = new Book();
        book.setBookTitle(bookTitle);
        book.setBookAuthor(bookAuthor);
        book.setPrice(price);
        book.setQuantity(quantity);
        bookService.addBook(book);

        return "success";
    }

    public String update() {
        book.setBookTitle(bookTitle);
        book.setBookAuthor(bookAuthor);
        book.setPrice(price);
        book.setQuantity(quantity);
        bookService.update(book);
        return "success";
    }

    public String delete() {
        bookService.delete(book);
        return "success";
    }

    public void addSelectedBook(Book book) {
        if (book != null) {
            this.selectedBook = book;
        }
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }
}
