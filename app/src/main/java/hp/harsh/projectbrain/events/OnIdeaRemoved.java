package hp.harsh.projectbrain.events;

public class OnIdeaRemoved {
    private int removedPosition;

    public OnIdeaRemoved() {
    }

    public OnIdeaRemoved(int removedPosition) {
        this.removedPosition = removedPosition;
    }

    public int getRemovedPosition() {
        return removedPosition;
    }

    public void setRemovedPosition(int removedPosition) {
        this.removedPosition = removedPosition;
    }
}
