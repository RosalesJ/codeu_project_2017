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

import codeu.chat.util.Serializer;
import codeu.chat.util.Serializers;
import codeu.chat.util.Time;
import codeu.chat.util.Uuid;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;


public final class Conversation {

  public static final Serializer<Conversation> SERIALIZER = new Serializer<Conversation>() {

    @Override
    public void write(OutputStream out, Conversation value) throws IOException {

      Uuid.SERIALIZER.write(out, value.id);
      Uuid.SERIALIZER.write(out, value.owner);
      Time.SERIALIZER.write(out, value.creation);
      Serializers.STRING.write(out, value.title);
      Serializers.collection(Uuid.SERIALIZER).write(out, value.users);
      Uuid.SERIALIZER.write(out, value.firstMessage);
      Uuid.SERIALIZER.write(out, value.lastMessage);

    }

    @Override
    public Conversation read(InputStream in) throws IOException {

      final Conversation value = new Conversation(
          Uuid.SERIALIZER.read(in),
          Uuid.SERIALIZER.read(in),
          Time.SERIALIZER.read(in),
          Serializers.STRING.read(in)
      );

      value.users.addAll(Serializers.collection(Uuid.SERIALIZER).read(in));

      value.firstMessage = Uuid.SERIALIZER.read(in);
      value.lastMessage = Uuid.SERIALIZER.read(in);

      return value;

    }
  };

  public final ConversationSummary summary;

  public final Uuid id;
  public final Uuid owner;
  public final Time creation;
  public final String title;
  public final Collection<Uuid> users = new HashSet<Uuid>();
  public Uuid firstMessage = Uuid.NULL;
  public Uuid lastMessage = Uuid.NULL;

  public Conversation(Uuid id, Uuid owner, Time creation, String title) {

    this.id = id;
    this.owner = owner;
    this.creation = creation;
    this.title = title;

    this.summary = new ConversationSummary(id, owner, creation, title);
  }

  @Override
  public int hashCode() { return id.hashCode(); }

  @Override
  public boolean equals(Object o) {
    return o instanceof Conversation && equals(this, (Conversation)o);
  }

  public static boolean equals(Conversation a, Conversation b) {
    if (a == b) {
      return true;
    }

    if ((a == null ^ b == null) ||
            (a.id == null ^ b.id == null) ||
            (a.owner == null ^ b.owner == null) ||
            (a.creation == null ^ b.creation == null) ||
            (a.title == null ^ b.creation == null)) {
      return false;
    }

    if ((a.id == b.id || a.id.equals(b.id)) &&
            (a.owner == b.owner || a.id.equals(b.id)) &&
            (a.creation == b.creation || a.creation.equals(b.creation)) &&
            (a.title == b.title || a.title.equals(b.title))
            ) {
      return true;
    }

    return false;
  }
}
