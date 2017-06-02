package codeu.chat.Database;

import codeu.chat.common.Conversation;
import codeu.chat.common.Message;
import codeu.chat.common.User;
import codeu.chat.util.Time;
import codeu.chat.util.Uuid;
import org.bson.Document;

import java.io.IOException;

public class Packer {
  /**
   * Convert a Document to a Message object
   * @param doc the Document to be converted
   * @return A Message derived from doc
   */
  public static Message unpackMessage(Document doc) {

    if (doc == null)
      return null;

    Uuid id = null;
    Uuid next = null;
    Uuid previous = null;
    Uuid author = null;
    Uuid conversation = null;

    try {
      id = Uuid.parse((String)doc.get("id"));
      next = Uuid.parse((String)doc.get("next"));
      previous = Uuid.parse((String)doc.get("previous"));
      author = Uuid.parse((String)doc.get("author"));
      conversation = Uuid.parse((String)doc.get("conversation"));
    }
    catch(IOException e) {
      System.out.println("Bad Uuid");
    }

    Long creation = (Long)doc.get("creation");

    return new Message(
            id,
            next,
            previous,
            Time.fromMs(creation == null ? 0 : creation),
            author,
            (String) doc.get("content"),
            conversation
    );
  }

  /**
   * Convert a Document to a User object
   * @param doc the Document to be converted
   * @return A User derived from doc
   */
  public static User unpackUser(Document doc) {
    if(doc == null)
      return null;
    Uuid id = null;

    try {
      id = Uuid.parse((String)doc.get("id"));
    }
    catch(IOException e) {
      System.out.println("Bad Uuid");
    }

    Long creation = (Long)doc.get("creation");

    return new User(id,
            (String)doc.get("name"),
            Time.fromMs(creation == null ? 0 : creation)
    );
  }

  /**
   * Covert a Document to a Conversation object
   * @param doc the Document to be converted
   * @return A Conversation derived from doc
   */
  public static Conversation unpackConversation(Document doc) {
    if(doc == null)
      return null;
    Uuid id = null;
    Uuid owner = null;

    try {
      id = Uuid.parse((String)doc.get("id"));
      owner = Uuid.parse((String)doc.get("owner"));
    }
    catch(IOException e) {
      System.out.println("Bad Uuid");
    }

    Long creation = (Long)doc.get("creation");

    return new Conversation(
            id,
            owner,
            Time.fromMs(creation == null ? 0 : creation),
            (String) doc.get("title")
    );
  }

  public static Document packMessage(Message message) {
    if(message == null)
      return null;

    String next = (message.next == null) ? null : message.next.toString();
    String previous = (message.previous == null) ? null : message.previous.toString();
    Long creation = (message.creation == null) ? null : message.creation.inMs();
    String author = (message.author == null) ? null : message.author.toString();
    String content = (message.content == null) ? null : message.content.toString();
    String conversation = (message.conversation == null) ? null : message.conversation.toString();

    Document document = new Document("id", message.id.toString())
            .append("next", next)
            .append("previous", previous)
            .append("creation", creation)
            .append("author", author)
            .append("content", content)
            .append("conversation",conversation);
    return document;
  }

  public static Document packUser(User user) {
    if (user == null)
      return null;

    String name = (user.name == null) ? null : user.name.toString();
    Long creation = (user.creation == null) ? null : user.creation.inMs();
    Document document = new Document("id", user.id.toString())
            .append("name", name)
            .append("creation", creation);
    return document;
  }

  public static Document packConversation(Conversation conversation) {
    if (conversation == null)
      return null;

    String id = (conversation.id == null) ? null : conversation.id.toString();
    String owner = (conversation.owner == null) ? null : conversation.owner.toString();
    Long creation = (conversation.creation == null) ? null : conversation.creation.inMs();
    String title = (conversation.title == null) ? null : conversation.title.toString();
    String firstMessage = (conversation.firstMessage == null) ? null : conversation.firstMessage.toString();
    String lastMessage = (conversation.lastMessage == null) ? null : conversation.lastMessage.toString();

    Document document = new Document("id", id)
            .append("owner", owner)
            .append("creation", creation)
            .append("title", title)
            .append("users", conversation.users)
            .append("firstMessage", firstMessage)
            .append("lastMessage", lastMessage);
    return document;
  }
}
