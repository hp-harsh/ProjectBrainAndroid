package hp.harsh.projectbrain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewIdeaModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("originalId")
    @Expose
    private String originalId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("context")
    @Expose
    private String context;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("author")
    @Expose
    private Author author;

    /**
     * No args constructor for use in serialization
     *
     */
    public NewIdeaModel() {
    }

    /**
     *
     * @param author
     * @param context
     * @param id
     * @param originalId
     * @param title
     * @param content
     */
    public NewIdeaModel(Integer id, String originalId, String title, String context, String content, Author author) {
        super();
        this.id = id;
        this.originalId = originalId;
        this.title = title;
        this.context = context;
        this.content = content;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public class Author {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("followers")
        @Expose
        private List<Object> followers = null;
        @SerializedName("todos")
        @Expose
        private List<Object> todos = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public Author() {
        }

        /**
         *
         * @param firstname
         * @param followers
         * @param todos
         * @param email
         * @param username
         * @param lastname
         */
        public Author(String email, String username, String firstname, String lastname, List<Object> followers, List<Object> todos) {
            super();
            this.email = email;
            this.username = username;
            this.firstname = firstname;
            this.lastname = lastname;
            this.followers = followers;
            this.todos = todos;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public List<Object> getFollowers() {
            return followers;
        }

        public void setFollowers(List<Object> followers) {
            this.followers = followers;
        }

        public List<Object> getTodos() {
            return todos;
        }

        public void setTodos(List<Object> todos) {
            this.todos = todos;
        }
    }
}