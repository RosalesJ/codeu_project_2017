package codeu.chat.database;

import codeu.chat.common.Conversation;
import codeu.chat.common.Message;
import codeu.chat.common.User;
import codeu.chat.util.Time;
import codeu.chat.util.Uuid;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Jacob Rosales Chase
 *
 * The following test suite will pass if and only if
 * messages can be packed and unpacked withough changing the message
 * and packed messages can be unpacked and repacked without changing the
 * packed message
 */
public class PackerTest {
  private Message message;
  private Conversation conversation;
  private User user;

  @Before
  public void doBefore() {
    message = new Message(new Uuid(1),new Uuid(2),new Uuid(3), Time.now(), new Uuid(4),"Hello World", new Uuid(39));
    conversation = new Conversation(new Uuid(5),new Uuid(6), Time.now(),"Hello world");
    user = new User(new Uuid(7),"Mark", Time.now());
  }


  @Test
  public void testUnpackMessage() {
    Message unpacked = Packer.unpackMessage(Packer.packMessage(message));

    assertTrue(message.equals(unpacked));
  }

  @Test
  public void testUnpackConversation() {
    Conversation unpacked = Packer.unpackConversation(Packer.packConversation(conversation));

    assertTrue(conversation.equals(unpacked));
  }

  @Test
  public void testUnpackUser() {
    User unpacked = Packer.unpackUser(Packer.packUser(user));

    assertTrue(user.equals(unpacked));
  }

  @Test
  public void testPackMessages() {
    Document packed = Packer.packMessage(message);
    Document repacked = Packer.packMessage(Packer.unpackMessage(packed));

    assertTrue(repacked.equals(packed));
  }

  @Test
  public void testPackConversations() {
    Document packed = Packer.packConversation(conversation);
    Document repacked = Packer.packConversation(Packer.unpackConversation(packed));

    assertTrue(repacked.equals(packed));
  }

  @Test
  public void testPackUsers() {
    Document packed = Packer.packUser(user);
    Document repacked = Packer.packUser(Packer.unpackUser(packed));

    assertTrue(repacked.equals(packed));
  }
}
