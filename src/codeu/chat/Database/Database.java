// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package codeu.chat.Database;

import codeu.chat.common.Conversation;
import codeu.chat.common.Message;
import codeu.chat.common.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoQueryException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jacob Rosales Chase
 *
 * A class that controlls a MongoDB database for
 * storing and retrieving Converations, Messages, and Users
 */
public class Database {
  public final MongoClient mongoClient;
  public final MongoDatabase database;
  public final MongoCollection<Document> users;
  public final MongoCollection<Document> messages;
  public final MongoCollection<Document> conversations;


  public Database(String path){
    //silence console output
    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);

    mongoClient = new MongoClient();
    database = mongoClient.getDatabase(path);

    users = database.getCollection("users");
    messages = database.getCollection("messages");
    conversations = database.getCollection("conversations");
  }

  /**
   * Convert a Message to document form and store in messages collection
   * @param message the message to store
   */
  public boolean write(Message message){
    try {
      messages.insertOne(Packer.packMessage(message));
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Convert a Message to Document form and store in conversations collections
   * @param conversation the conversation to be stored
   */
  public boolean write(Conversation conversation){
    try {
      conversations.insertOne(Packer.packConversation(conversation));
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Convert a User to Document form and store in users collection
   * @param user the user to be stored
   */
  public boolean write(User user) {
    try {
      users.insertOne(Packer.packUser(user));
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean removeMessage(String id) {
    boolean result;
    try {
      result = messages.deleteOne(Filters.eq("id", id)).getDeletedCount() == 1 ? true : false;
    }
    catch (MongoWriteException e) {
      return false;
    }
    return result;
  }

  public boolean removeConversation(String id) {
    boolean result;
    try {
      result = conversations.deleteOne(Filters.eq("id", id)).getDeletedCount() == 1 ? true : false;
    }
    catch (MongoWriteException e) {
      return false;
    }
    return result;
  }

  public boolean removeUser(String id) {
    boolean result;
    try {
      result = users.deleteOne(Filters.eq("id", id)).getDeletedCount() == 1 ? true : false;
    }
    catch (MongoWriteException e) {
      return false;
    }
    return result;
  }

  public Collection<User> findUser(String identifier) {
    try {
      List<User> foundUsers = new ArrayList<User>();
      Iterable<Document> foundDocs = users.find(Filters.text(identifier));

      for(Document doc : foundDocs) {
        foundUsers.add(Packer.unpackUser(doc));
      }
      return foundUsers;
    }
    catch (MongoQueryException e) {
      return new ArrayList<User>();
    }
  }

  public Collection<Message> findMessage(String identifier) {
    try {
      Iterable<Document> foundDocs= messages.find(Filters.text(identifier));
      List<Message> foundMessages = new ArrayList<Message>();

      for(Document doc : foundDocs) {
        foundMessages.add(Packer.unpackMessage(doc));
      }
      return foundMessages;
    }
    catch (MongoQueryException e) {
      return new ArrayList<Message>();
    }
  }

  public Collection<Conversation> findConversation(String identifier) {
    try {
      Iterable<Document> foundDocs = conversations.find(Filters.text(identifier));
      List<Conversation> foundConversations = new ArrayList<Conversation>();

      for(Document doc : foundDocs) {
        foundConversations.add(Packer.unpackConversation(doc));
      }
      return foundConversations;
    }
    catch (MongoQueryException e) {
      return new ArrayList<Conversation>();
    }
  }

  /**
   * Return a collection of Users from the Database
   * @param limit the maximum number of users in the collection
   * @return an ArrayList of Users
   */
  public Collection<User> getUsers(int limit) {
    ArrayList<User> returnedUsers = new ArrayList<User>();

    for(Document doc : users.find()) {
      if (limit-- == 0) break;
      returnedUsers.add(Packer.unpackUser(doc));
    }
    return returnedUsers;
  }

  /**
   * Return a collection of Messages from the Database
   * @param limit the maximum number of messages in the collection
   * @return an ArrayList of Users
   */
  public Collection<Message> getMessages(int limit) {
    ArrayList<Message> returnedMessages = new ArrayList<Message>();

    for (Document doc : messages.find()) {
      if (limit -- == 0) break;
      returnedMessages.add(Packer.unpackMessage(doc));
    }
    return returnedMessages;
  }

  /**
   * Return a collection of Conversations from the Database
   * @param limit the maximum number of conversations int he collection
   * @return
   */
  public Collection<Conversation> getConversations(int limit) {
    ArrayList<Conversation> returnedConversation = new ArrayList<Conversation>();

    for (Document doc : conversations.find()) {
      if (limit -- == 0) break;
      returnedConversation.add(Packer.unpackConversation(doc));
    }
    return returnedConversation;
  }
}