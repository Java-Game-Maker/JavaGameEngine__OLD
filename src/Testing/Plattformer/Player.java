package Testing.Plattformer;

import JavaGameEngine.Display.CalcThread;
import JavaGameEngine.Msc.Input.Input;
import JavaGameEngine.Msc.Input.Keys;
import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Collision.SquareCollider;
import JavaGameEngine.Objects.Components.Physics.PhysicsBody;
import JavaGameEngine.Objects.Components.GameObject;
import JavaGameEngine.Objects.Components.Visual.Animation;

import java.util.Vector;

public class Player extends GameObject {

    private float speed =2;
    private float gravity=0;
    private float jump=4;
    private boolean isGrounded = false;
    private boolean jumping=false;

    private float shootTimer = 10;
    Vector2 shootAtt = new Vector2(0,0);
    
    public Player(Vector2 vector2) {
        super(vector2);
        setScale(new Vector2(100,100));
        Animation a = new Animation();
        a.setPath("/spritesheet.png");
        a.setTILE_SIZE(new Vector2(228,251));
        a.loadAnimation(new Vector2[]{new Vector2(0,0),new Vector2(0,1)});
        a.setAngle(90);
        addComponent(a);
        addComponent(new PhysicsBody());
        SquareCollider s = new SquareCollider();
        s.setVisible(false);
        addComponent(s);
        s.setScale(new Vector2(50,getScale().getY()));

    }
    private void jump() {

    }
    private void shoot()
    {
        float angle = (float) this.LookAt(Input.getMousePosition());
        //System.out.println(angle);
        long start = System.nanoTime();

        CalcThread.newObjects.add(new Bullet(getPosition().add(Vector2.right.multiply(getScale().getX())),getDirection().getDirection(angle)));

        long end = System.nanoTime();
        //System.out.println((end-start)/1000000);

    }

    private float x = 1;
    private float y = 1;
    private void movement()
    {
        if(Input.isKeyDown(Keys.W))
        {
           
       
        }
        if(Input.isKeyDown(Keys.D))
        {
        
            setDirection(Vector2.right);
            setDirection(movePosition(getPosition().add(getDirection().multiply(speed))));
        }
        if(Input.isKeyDown(Keys.A))
        {
            setDirection(Vector2.left);
            setDirection(movePosition(getPosition().add(getDirection().multiply(speed))));

        }
        if(Input.isKeyDown(Keys.SPACE))
        {if(shootTimer<0)
        {
            shoot();
            shootTimer=10;

        }
            shootTimer-=1;
            //setDirection(Vector2.up);
        }

    }
    @Override
    public void Update() {
        super.Update();
        movement();
        //System.out.println("Update player");
    }
}
