package nl.sogyo.javaopdrachten.raytracer;

import nl.sogyo.javaopdrachten.raytracer.vectors.*;

public class SceneBuilder 
{
	private Scene scene;
	
	public SceneBuilder(Viewport viewport)
	{
		scene = new Scene(viewport);
	}
	
	public SceneBuilder()
	{
		this(new Viewport(
				new Rect(new Vector3(-1.6, 0.9, -5), new Vector3(1.6, 0.9, -5), new Vector3(1.6, -0.9, -5)),
				new Vector2Int(1280, 720),
				new Vector3(0,0,-10)
				));
	}
	
	public SceneBuilder withAmbientLight(Color ambientLight)
	{
		scene.ambientLight = ambientLight;
		return this;
	}
	
	public SceneBuilder withBackgroundColor(Color backgroundColor)
	{
		scene.backgroundColor = backgroundColor;
		return this;
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
	public <T extends ObjectBuilder<?>> SceneBuilder withObject(ObjectBuilder<?> objectBuilder)
	{
		scene.addObject(objectBuilder.getObject());
		return this;
	}
	
	public static abstract class ObjectBuilder<T extends SceneObject>
	{
		protected T object;
		
		public T getObject()
		{
			return object;
		}
	}
}
