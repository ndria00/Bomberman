package application.model;

public class GameObject {
    private Position position;

    public GameObject(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return  position;
    }

    public void setPosition(Position position){
        this.position = position;
    }
}
