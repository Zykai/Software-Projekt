package Player;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.util.ArrayList;

import Enemies.Enemy;
import Entity.Animation;
import Entity.Entity;
import Maps.Map;

public class MageProjectile extends Entity{

    private static Animation PROJECTILE_MOVE;
    public static double WIDTH = 40, HEIGHT = 20;

    public static ArrayList<Entity> hitList;

    private float age = 0;

    static{
        PROJECTILE_MOVE = new Animation("res/monster/Necromancer/projectile/necromancer-projectile-projectile-0", 3, 300);
    }

    public MageProjectile(double playerX, double playerY, double x, double y){
        super();
        floating = true;
        this.xPosition = playerX;
        this.yPosition = playerY;
        this.width = 40;
        this.height = 40;
        this.moveDif((int)(x-playerX), (int)(y-playerY), false);
        hitList = new ArrayList<>(8);
        this.movespeed = 60;
    }

    public boolean canDamage(Entity e){
        for(int i = 0; i < hitList.size(); i++){
            if(e == hitList.get(i)){
                return false;
            }
        }
        hitList.add(e);
        return true;
    }

    public boolean updateProjectile(float deltaTime, Map map){
        super.update(deltaTime, map);
        age += deltaTime;
        return age > 10000;
    }

	@Override
	protected Animation getMove() {
		return PROJECTILE_MOVE;
	}

	@Override
	protected Animation getIdle() {
		return PROJECTILE_MOVE;
	}

	@Override
	protected Animation getAttack() {
		return null;
	}

	@Override
	protected Animation getDie() {
		return null;
    }
    
    public void draw(Graphics g, int xoffset, int yoffset){
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform trans = new AffineTransform();
        trans.translate(this.xPosition + xoffset+xdirection, this.yPosition + yoffset+ydirection);
        trans.rotate( Math.atan2(-this.ydirection, -this.xdirection));
        trans.scale(1, 2);

        g2d.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), trans, null);
    }

}