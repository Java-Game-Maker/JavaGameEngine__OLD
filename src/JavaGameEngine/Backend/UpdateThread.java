package JavaGameEngine.Backend;

import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.util.LinkedList;

public class UpdateThread extends Thread{

    private static LinkedList<Component> objects = new LinkedList<>();
    public static LinkedList<Component> newObjects = new LinkedList<>();
    public static LinkedList<Component> delObjects = new LinkedList<>();

    public void setObjects(LinkedList<Component>  objects) {
        UpdateThread.objects = objects;
    }

    public UpdateThread(LinkedList<Component> o) {
        this.setObjects(o);
    }

    public static Component camera = new Component();

    private LinkedList<Component>  UpdateObjects()
    {

        for (Component component : ComponentHandler.getObjects()) {

            component.setCameraPosition(UpdateThread.camera.getPosition());
            component.update();
        }
        return ComponentHandler.getObjects();
    }

    public void Update() {
        ComponentHandler.setObjects(UpdateObjects());
        if(UpdateThread.newObjects.size()>0) {
            for (Component o : UpdateThread.newObjects) {
                ComponentHandler.addObject(o);
            }
            newObjects.clear();
        }
        if(UpdateThread.delObjects.size()>0) {
            for (Component o : UpdateThread.delObjects) {
                ComponentHandler.removeObject(o);
            }
            delObjects.clear();
        }
    }

    @Override
    public void run() {
        super.run();
        ComponentHandler.setObjects(UpdateObjects());
    }
}

