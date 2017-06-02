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
import codeu.chat.util.Time;
import codeu.chat.util.Uuid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Jacob Rosales Chase
 *
 * A testing Suite for the Database Class
 */
public final class DatabaseTest {
  private static Database database;
  private static Collection<Message> messages;
  private static Collection<User> users;
  private static Collection<Conversation> conversations;

  @Before
  public void doBefore() {
    database = new Database("testdb");

    messages = new ArrayList<Message>();
    users = new ArrayList<User>();
    conversations = new ArrayList<Conversation>();

    for(int i = 0; i < 3; i ++) {
      messages.add(new Message(new Uuid(i), new Uuid(i*10), new Uuid(i*100), Time.now(), new Uuid(i*1000), "Hello World" + i, new Uuid(i*10000)));
      conversations.add(new Conversation(new Uuid(i), new Uuid(i*10), Time.now(), "convo" + i));
      users.add(new User(new Uuid(i), "user" + i, Time.now()));
    }
  }

  @After
  public void doAfter() {
    database.users.drop();
    database.messages.drop();
    database.conversations.drop();
  }

  @Test
  public void testWriteMessage() {
    int i = 0;
    for (Message m : messages) {
      assertTrue(database.messages.count() == i);
      assertTrue(database.writeMessage(m));
      assertTrue(database.messages.count() == i + 1);
      i++;
    }
  }

  @Test
  public void testWriteConversation() {
    int i = 0;
    for (Conversation c : conversations) {
      assertTrue(database.conversations.count() == i);
      assertTrue(database.writeConversation(c));
      assertTrue(database.conversations.count() == i + 1);
      i++;
    }
  }

  @Test
  public void testWriteUser() {
    int i = 0;
    for (User u : users) {
      assertTrue(database.users.count() == i);
      assertTrue(database.writeUser(u,"nothing"));
      assertTrue(database.users.count() == i + 1);
      i++;
    }
  }

  @Test
  public void testFindMessage() {
    for (Message m : messages) {
      database.writeMessage(m);
    }
    assertTrue(database.findMessage("not a message").isEmpty());

    Collection<Message> found;

    for (int i = 0; i < 3; i++) {
      //find by user name
      found = database.findMessage("user" + i);
      assertTrue(messages.containsAll(found));
      //find by user id
      found = database.findMessage("" + i);
      assertTrue(messages.containsAll(found));
    }
  }

  @Test
  public void testFindConversation() {
    for (Conversation m : conversations) {
      database.writeConversation(m);
    }
    assertTrue(database.findConversation("not a convo").isEmpty());

    Collection<Conversation> found;

    for (int i = 0; i < 3; i++) {
      //find by user name
      found = database.findConversation("user" + i);
      assertTrue(conversations.containsAll(found));
      //find by user id
      found = database.findConversation("" + i);
      assertTrue(conversations.containsAll(found));
    }
  }

  @Test
  public void testFindUser() {
    for (User m : users) {
      database.writeUser(m,"nothing");
    }
    assertTrue(database.findUser("not a user").isEmpty());
    Collection<User> found;

    for (int i = 0; i < 3; i++) {
      //find by user name
      found = database.findUser("user" + i);
      assertTrue(users.containsAll(found));
      //find by user id
      found = database.findUser("" + i);
      assertTrue(users.containsAll(found));
    }
  }

  @Test
  public void testRemoveMessage() {
    for (Message m : messages) {
      database.writeMessage(m);
    }
    assertFalse("Remove an item that doesn't exist",database.removeMessage("not a message"));
    for(int i = 0; i < 3; i++) {
      assertTrue("Remove message " + i, database.removeMessage("" + i));
      assertTrue("Check number of items in the database: " + (2 - i),database.messages.count() == 2 - i);
    }
    //database is empty here
    assertFalse("Remove from an empty list", database.removeMessage("some message"));
  }

  @Test
  public void testRemoveConversation() {
    for (Conversation m : conversations) {
      database.writeConversation(m);
    }
    assertFalse("Remove an item that doesn't exist",database.removeConversation("10000"));

    for(int i = 0; i < 3; i++) {
      assertTrue("Remove conversation " + i , database.removeConversation("" + i));
      assertTrue("Check number of items in the database: " + (2 - i), database.conversations.count() == 2 - i);
    }
    //database is empty here
    assertFalse("Remove from an empty list", database.removeConversation("1"));
  }

  @Test
  public void testRemoveUser() {
    for (User m : users) {
      database.writeUser(m,"nothing");
    }
    assertFalse("Remove an item that doesn't exist",database.removeUser("100000"));
    for(int i = 0; i < 3; i++) {
      assertTrue("Remove user" + i, database.removeUser("" + i));
      assertTrue("Check number of items in the database: " + (2 - i), database.users.count() == 2 - i);
    }
    //database is empty here

    assertFalse("Remove from an empty list",database.removeUser("1"));
  }

  @Test
  public void testGetMessages() {
    Collection<Message> retrieved;
    for(int i = 2; i <=4;i++) {
      retrieved = database.getMessages(i);
      assertTrue(messages.containsAll(retrieved));
    }
  }

  @Test
  public void testGetConversations() {
    Collection<Conversation> retrieved;
    for(int i = 2; i <=4;i++) {
      retrieved = database.getConversations(i);
      assertTrue(conversations.containsAll(retrieved));
    }
  }

  @Test
  public void testGetUsers() {
    Collection<User> retrieved;
    for(int i = 2; i <=4;i++) {
      retrieved = database.getUsers(i);
      assertTrue(users.containsAll(retrieved));
    }
  }
}
