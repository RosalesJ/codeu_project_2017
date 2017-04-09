package codeu.chat.Database;

import codeu.chat.common.BasicController;
import codeu.chat.common.Conversation;
import codeu.chat.common.Message;
import codeu.chat.common.User;
import codeu.chat.server.Controller;
import codeu.chat.server.Model;
import codeu.chat.util.Uuid;
import static org.junit.Assert.*;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jacob Rosales Chase
 *
 * The following test suite will pass if and only if
 * messages can be packed and unpacked withough changing the message
 * and packed messages can be unpacked and repacked without changing the
 * packed message
 */
public class PackerTest {
  private Model model;
  private BasicController controller;

  @Before
  public void doBefore() {
    model = new Model();
    controller = new Controller(Uuid.NULL, model);
  }


  @Test
  public void testUnpackMessage() {
    Message message = controller.newMessage(new Uuid(1),new Uuid(2),"Hello World");
    Message unpacked = Packer.unpackMessage(Packer.packMessage(message));

    assertTrue(message.equals(unpacked));
  }

  @Test
  public void testUnpackConversation() {
    Conversation conversation = controller.newConversation("Hello world", Uuid.NULL);
    Conversation unpacked = Packer.unpackConversation(Packer.packConversation(conversation));

    assertTrue(conversation.equals(unpacked));
  }

  @Test
  public void testUnpackUser() {
    User user = controller.newUser("Mark");
    User unpacked = Packer.unpackUser(Packer.packUser(user));

    assertTrue(user.equals(unpacked));
  }

  @Test
  public void testPackMessages() {
    Document packed = Packer.packMessage(controller.newMessage(new Uuid(1),new Uuid(2),"Hello World"));
    Document repacked = Packer.packMessage(Packer.unpackMessage(packed));

    assertTrue(repacked.equals(packed));
  }

  @Test
  public void testPackConversations() {
    Document packed = Packer.packConversation(controller.newConversation("Hello world", Uuid.NULL));
    Document repacked = Packer.packMessage(Packer.unpackMessage(packed));

    assertTrue(repacked.equals(packed));
  }

  @Test
  public void testPackUsers() {
    Document packed = Packer.packUser(controller.newUser("Mark"));
    Document repacked = Packer.packMessage(Packer.unpackMessage(packed));

    assertTrue(repacked.equals(packed));
  }
}
