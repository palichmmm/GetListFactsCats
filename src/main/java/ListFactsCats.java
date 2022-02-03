import com.fasterxml.jackson.annotation.JsonProperty;

public class ListFactsCats {
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final int upvotes;

    public ListFactsCats(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") int upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "ListFactsCats{" +
                "id='" + id + '\'' +
                ", upvotes=" + upvotes +
                ", user='" + user + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
