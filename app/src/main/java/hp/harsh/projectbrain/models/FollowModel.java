package hp.harsh.projectbrain.models;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowModel {

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
    private List<Follower> followers = null;
    @SerializedName("todos")
    @Expose
    private List<Object> todos = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public FollowModel() {
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
    public FollowModel(String email, String username, String firstname, String lastname, List<Follower> followers, List<Object> todos) {
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

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public List<Object> getTodos() {
        return todos;
    }

    public void setTodos(List<Object> todos) {
        this.todos = todos;
    }

    public class Follower {

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
        public Follower() {
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
        public Follower(String email, String username, String firstname, String lastname, List<Object> followers, List<Object> todos) {
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