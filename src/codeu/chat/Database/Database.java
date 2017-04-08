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
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Collection;

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
  public void write(Message message){
    messages.insertOne(Packer.packMessage(message));
  }

  /**
   * Convert a Message to Document form and store in conversations collections
   * @param conversation the conversation to be stored
   */
  public void write(Conversation conversation){
    conversations.insertOne(Packer.packConversation(conversation));
  }

  /**
   * Convert a User to Document form and store in users collection
   * @param user the user to be stored
   */
  public void write(User user) {
    users.insertOne(Packer.packUser(user));
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