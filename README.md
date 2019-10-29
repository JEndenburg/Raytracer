Raytracer
=====

A simple Java raytracer application that can render spheres. Part of the Sogyo BV "Learning to Program" optional advanced exercises.

Rendering
---
Default hardcoded output path for the image is /output/GeneratedImage.png

Images are generated starting from a "viewport", which casts rays out from every pixel in the image. If the ray intersects with any object, more rays are cast out from the intersection towards all light sources within the scene. If this ray does *not* intersect any other object, a calculation is done based on the object's given properties, and the result is output to the pixel on the image.

The project contains builders with fluent interfacing to quickly setup a scene.
