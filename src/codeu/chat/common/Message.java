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

package codeu.chat.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import codeu.chat.util.Serializer;
import codeu.chat.util.Serializers;
import codeu.chat.util.Time;
import codeu.chat.util.Uuid;

public final class Message {

  public static final Serializer<Message> SERIALIZER = new Serializer<Message>() {

    @Override
    public void write(OutputStream out, Message value) throws IOException {

      Uuid.SERIALIZER.write(out, value.id);
      Uuid.SERIALIZER.write(out, value.next);
      Uuid.SERIALIZER.write(out, value.previous);
      Time.SERIALIZER.write(out, value.creation);
      Uuid.SERIALIZER.write(out, value.author);
      Serializers.STRING.write(out, value.content);

    }

    @Override
    public Message read(InputStream in) throws IOException {

      return new Message(
          Uuid.SERIALIZER.read(in),
          Uuid.SERIALIZER.read(in),
          Uuid.SERIALIZER.read(in),
          Time.SERIALIZER.read(in),
          Uuid.SERIALIZER.read(in),
          Serializers.STRING.read(in)
      );

    }
  };

  public final Uuid id;
  public final Uuid previous;
  public final Time creation;
  public final Uuid author;
  public final String content;
  public Uuid next;

  public Message(Uuid id, Uuid next, Uuid previous, Time creation, Uuid author, String content) {

    this.id = id;
    this.next = next;
    this.previous = previous;
    this.creation = creation;
    this.author = author;
    this.content = content;

  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Message && equals(this, (Message) o);
  }

  public static boolean equals(Message a, Message b) {
    if (a == b) {
      return true;
    }

    //checks if one is null and not the other
    if ((a == null ^ b == null) ||
            (a.id == null ^ b.id == null) ||
            (a.next == null ^ b.id == null) ||
            (a.previous == null ^ b.id == null) ||
            (a.creation == null ^ b.creation == null) ||
            (a.author == null ^ b.author == null) ||
            (a.content == null ^ b.content == null)
            ) {
      return false;
    }

    //checks if all the fields are equal
    if ((a.id == b.id || a.id.equals(b.id)) &&
            (a.next == b.next || a.next.equals(b.next)) &&
            (a.previous == b.previous || a.previous.equals(b.previous)) &&
            (a.creation == b.creation || a.creation.equals(b.creation)) &&
            (a.author == b.author || a.author.equals(b.author)) &&
            (a.content == b.content || a.content.equals(b.content))
            ) {
      return true;
    }

    return false;
  }
}
