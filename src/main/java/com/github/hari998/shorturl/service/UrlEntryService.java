package com.github.hari998.shorturl.service;

import com.github.hari998.shorturl.entity.UrlEntry;
import com.github.hari998.shorturl.repository.UrlEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UrlEntryService {

    @Autowired
    private UrlEntryRepository urlEntryRepository;

    public List<UrlEntry> getAll() {
        return urlEntryRepository.findAll();
    }

    public UrlEntry saveUrlEntry(UrlEntry urlEntry) {
        UrlEntry urlEntryResp = urlEntryRepository.save(urlEntry);
        return urlEntryResp;
    }

    public Optional<UrlEntry> findById(ObjectId id) {
        return urlEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        urlEntryRepository.deleteById(id);
    }
}
