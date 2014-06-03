package cassandra;

import java.util.UUID;

/**
 * Created by alexandra on 03/06/2014.
 */
public class Cat {

    private UUID id;
    private String name;
    private String nickname;

    public Cat(UUID id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (id != null ? !id.equals(cat.id) : cat.id != null) return false;
        if (name != null ? !name.equals(cat.name) : cat.name != null) return false;
        if (nickname != null ? !nickname.equals(cat.nickname) : cat.nickname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }
}
