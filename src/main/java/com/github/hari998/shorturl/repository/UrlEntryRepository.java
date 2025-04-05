package com.github.hari998.shorturl.repository;

import com.github.hari998.shorturl.entity.UrlEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlEntryRepository extends MongoRepository<UrlEntry, ObjectId> {

}
