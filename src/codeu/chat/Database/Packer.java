package codeu.chat.Database;
import codeu.chat.util.Time;
import codeu.chat.util.Uuid;
import codeu.chat.common.Conversation;
import codeu.chat.common.Message;
import codeu.chat.common.User;
import org.bson.Document;

public class Packer {
  /**
   * Convert a Document to a Message object
   * @param doc the Document to be converted
   * @return A Message derived from doc
   */
  public static Message unpackMessage(Document doc){
    return new Message(
            (Uuid) doc.get("id"),
            (Uuid) doc.get("next"),
            (Uuid) doc.get("previous"),
            (Time) doc.get("creation"),
            (Uuid) doc.get("author"),
            (String) doc.get("content")
    );
  }

  /**
   * Convert a Document to a User object
   * @param doc the Document to be converted
   * @return A User derived from doc
   */
  public static User unpackUser(Document doc) {
    return new User(
            (Uuid) doc.get("id"),
            (String) doc.get("name"),
            (Time) doc.get("time")
    );
  }

  /**
   * Covert a Document to a Conversation object
   * @param doc the Document to be converted
   * @return A Conversation derived from doc
   */
  public static Conversation unpackConversation(Document doc) {
    return new Conversation(
            (Uuid) doc.get("id"),
            (Uuid) doc.get("owner"),
            (Time) doc.get("creation"),
            (String) doc.get("title")
    );
  }

  public static Document packMessage(Message message) {
    Document document = new Document("id", message.id)
            .append("next", message.next)
            .append("previous", message.previous)
            .append("creation", message.creation)
            .append("author", message.author)
            .append("content", message.content);
    return document;
  }

  public static Document packUser(User user) {
    Document document = new Document("id", user.id)
            .append("name",user.name)
            .append("creation", user.creation);
    return document;
  }

  public static Document packConversation(Conversation conversation) {
    Document document = new Document("id", conversation.id)
            .append("owner", conversation.owner)
            .append("creation", conversation.creation)
            .append("title", conversation.title)
            .append("users", conversation.users)
            .append("firstMessage", conversation.firstMessage)
            .append("lastMessage", conversation.lastMessage);
    return document;
  }
}
