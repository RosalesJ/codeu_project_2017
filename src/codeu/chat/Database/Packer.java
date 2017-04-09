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
            Uuid.fromString((String)doc.get("id")),
            Uuid.fromString((String)doc.get("next")),
            Uuid.fromString((String)doc.get("previous")),
            Time.fromMs((Long)doc.get("creation")),
            Uuid.fromString((String)doc.get("author")),
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
            Uuid.fromString((String)doc.get("id")),
            (String) doc.get("name"),
            Time.fromMs(((Long) doc.get("time")))
    );
  }

  /**
   * Covert a Document to a Conversation object
   * @param doc the Document to be converted
   * @return A Conversation derived from doc
   */
  public static Conversation unpackConversation(Document doc) {
    return new Conversation(
            Uuid.fromString((String) doc.get("id")),
            Uuid.fromString((String) doc.get("owner")),
            Time.fromMs((Long)doc.get("creation")),
            (String) doc.get("title")
    );
  }

  public static Document packMessage(Message message) {
    Document document = new Document("id", message.id.toString())
            .append("next", message.next.toString())
            .append("previous", message.previous.toString())
            .append("creation", message.creation.inMs())
            .append("author", message.author.toString())
            .append("content", message.content.toString());
    return document;
  }

  public static Document packUser(User user) {
    Document document = new Document("id", user.id.toString())
            .append("name", user.name)
            .append("creation", user.creation.inMs());
    return document;
  }

  public static Document packConversation(Conversation conversation) {
    Document document = new Document("id", conversation.id.toString())
            .append("owner", conversation.owner.toString())
            .append("creation", conversation.creation.inMs())
            .append("title", conversation.title)
            .append("users", conversation.users)
            .append("firstMessage", conversation.firstMessage.toString())
            .append("lastMessage", conversation.lastMessage.toString());
    return document;
  }
}
