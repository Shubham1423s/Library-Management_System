package com.Shubham.LibraryManagementSystem.controller;

import com.Shubham.LibraryManagementSystem.Model.Book;
import com.Shubham.LibraryManagementSystem.repo.Bookrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class BookController {
     @Autowired// we have provided the dependencies here
     Bookrepo bookrepo;

     @GetMapping("/getAllBooks")
     public ResponseEntity<List<Book>> getALlBooks(){
         try {
             List<Book> bookList = new ArrayList<>();
             bookrepo.findAll().forEach(bookList::add);

             if(bookList.isEmpty()){
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             }
             return new ResponseEntity<>(bookList,HttpStatus.OK);

         } catch (Exception ex){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

         }

     }
     @GetMapping("/getBooksById/{id}")
     public  ResponseEntity<Book> getBooksById(@PathVariable Long id){
         Optional<Book> bookData = bookrepo.findById(id);


         if(bookData.isPresent()){
             return new ResponseEntity<>(bookData.get(),HttpStatus.OK);

         }
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

     }
     @PostMapping("/addBook")
     public ResponseEntity<Book> addBook(@RequestBody Book book){

         Book bookObj = bookrepo.save(book);
         return  new ResponseEntity<>(bookObj,HttpStatus.OK);


     }
     @PostMapping("/updateBookById/{id}")
     public  ResponseEntity<Book>updateBookById(@PathVariable Long id, @RequestBody Book newBookData){
         Optional<Book> oldbookData = bookrepo.findById(id);

         if(oldbookData.isPresent()){
             Book updatedBookData = oldbookData.get();
             updatedBookData.setTitle(newBookData.getTitle());
             updatedBookData.setAuthor(newBookData.getAuthor());


          Book bookObj =   bookrepo.save(updatedBookData);
             return  new ResponseEntity<>(bookObj,HttpStatus.OK);


         }
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);


     }
     @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id){
         bookrepo.deleteById(id);
         return new ResponseEntity<>(HttpStatus.OK);


    }


}
