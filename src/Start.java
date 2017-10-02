import java.util.ArrayList;
import java.util.List;

import entity.Bug;
import entity.Entity;
import entity.Light;
import graphics.render.RenderHandler;

public class Start {
	
	public static void main(String[] args) {		
		
		RenderHandler batch = new RenderHandler(graphics.render.Canvas.getInstance());

		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < 50; i++) {
			entities.add(new Bug());
		}
		
		Light light = new Light(200);
		entities.add(light);
		
		for (Entity entity : entities) {
			batch.add(entity);
		}
		
		long previousMS = System.currentTimeMillis();
		while (true) {
			long currentMS = System.currentTimeMillis();
			if (currentMS - previousMS > 5000) {
				light.toggleState();
				previousMS = currentMS;
			}

			for (Entity entity : entities) {
				if (entity instanceof Light) {
					entity.update();
				} else if (entity instanceof Bug) {
					((Bug) entity).update(light);
				}
			}

			batch.render();
			
			try {
				Thread.sleep(17);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}