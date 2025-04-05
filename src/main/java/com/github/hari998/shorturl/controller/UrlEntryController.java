package com.github.hari998.shorturl.controller;

import com.github.hari998.shorturl.entity.UrlEntry;
import com.github.hari998.shorturl.service.UrlEntryService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UrlEntryController {

    @Autowired
    private UrlEntryService urlEntryService;


    @GetMapping("/getall")
    public List<UrlEntry> getAll() {
        return urlEntryService.getAll();
    }


    @GetMapping("/id/{id}")
    public UrlEntry getUrlEntryById(@PathVariable ObjectId id) {
        return urlEntryService.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public UrlEntry create(@RequestBody UrlEntry urlEntry){
        urlEntry.setCreatedAt(LocalDateTime.now());
        urlEntry.setUpdatedAt(LocalDateTime.now());
        UrlEntry urlEntryResp = urlEntryService.saveUrlEntry(urlEntry);
        return urlEntryResp;
//        return urlEntryService.saveUrlEntry(urlEntry);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/id/{id}")
    public ResponseEntity<UrlEntry> updateById(@RequestBody UrlEntry newUrlEntry, @PathVariable ObjectId id){
//        newUrlEntry.setUpdatedAt(LocalDateTime.now());
//        if(null == urlEntryService.findById(id)) {
//            return
//        }
//        urlEntryService.saveUrlEntry(newUrlEntry);
//        return null;
        Optional<UrlEntry> existingEntry = urlEntryService.findById(id);

        if (existingEntry.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }

        UrlEntry updatedEntry = urlEntryService.saveUrlEntry(newUrlEntry);
        return ResponseEntity.ok(updatedEntry);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/id/{id}")
    public boolean delete(@PathVariable ObjectId id){
        urlEntryService.deleteById(id);
        return true;
    }
}
