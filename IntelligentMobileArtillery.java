package imba;
import robocode.*;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * IntelligentMobileArtillery - a robot by (your name here)
 */
public class IntelligentMobileArtillery extends Robot
{
	int count = 0;
	double gunTurnAmt; // How much to turn our gun when searching
	String trackName;
	double moveAmount; // How much to move
	double randomMove;
	int randomDirection;
	/**
	 * run: IntelligentMobileArtillery's default behavior
	 */
public void run() {
		setBodyColor(Color.orange);
		setGunColor(Color.orange);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.red);
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());


		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		
		turnRight(90);
		turnGunRight(90);
		
		while (true) {
			//randomMove = Math.floor(Math.random() * moveAmount) + 1;
			turnGunRight(360);

			ahead(moveAmount/2);

			turnRight(90);			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
		if (Math.abs(bearingFromGun) <= 8) {
			turnGunRight(bearingFromGun);
				fire(3);fire(3);fire(3);
		}
		else {
			turnGunRight(bearingFromGun);
		}
		ahead(50);
		if (bearingFromGun == 0) {
			scan();
		}
	}

	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		}
		else {
			ahead(100);
		}
	}	

	public void onHitByBullet(HitByBulletEvent e){
		//turnRight(90);

	}
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		//back(20);
	}	
}
