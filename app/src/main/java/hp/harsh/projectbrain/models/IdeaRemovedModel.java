package hp.harsh.projectbrain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdeaRemovedModel {

    @SerializedName("id")
    @Expose
    private String id;

    /**
     * No args constructor for use in serialization
     *
     */
    public IdeaRemovedModel() {
    }

    /**
     *
     * @param id
     */
    public IdeaRemovedModel(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}