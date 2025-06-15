# KArtliner

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat&logo=android&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green)

**KArtliner** is an experimental Android project that transforms ultra-short mathematical functions into real-time graphic animations using Jetpack Compose.

Inspired by Processing, p5.js, code golf, and generative art, KArtliner lets you create complex visual shapes from one-liner functions or compact expressions.


---


## 💡 Concept

> A mathematical function → a visual world.

KArtliner offers a minimal environment to create dynamic drawings from mathematical expressions, in the spirit of:

- ✍️ function drawing: (i, t) → Offset(x, y)
- 🧪 creative code golf: a single line of code generating an animation
- ⚡ real-time rendering using Jetpack Compose Canvas
- 🧠 visual mathematical experimentation on mobile

---

## ✨ Features

- Smooth animation at 30–60 fps powered by Compose’s InfiniteTransition
- Support for parameterized math functions (i, t, size) → Offset
- Efficient vector rendering via Compose Canvas (Skia GPU accelerated)
- Modular, flexible architecture for easy testing of new formulas
- Smart load management: max 3k–5k points per frame to maintain fluidity
- Dynamic parsing of String expressions for live formula editing and testing
- Save and share your functions as compact one-liner strings


---

## 🔣 Sample Kotlin function

```kotlin
val waveFunc: GenerativeFunc = { i, t, _ ->
    val x = i.toFloat()
    val y = i / 235f
    val k = (4f + sin(y * 2f - t) * 3f) * cos(x / 29f)
    val e = y / 8f - 13f
    val d = hypot(k, e)
    val q = 3f * sin(k * 2f) + 0.3f / d + sin(y / 25f) * k * (9f + 4f * sin(e * 9f - d * 3f + t * 2f))
    val c = d - t
    val px = q + 30f * cos(c) + 200f
    val py = q * sin(c) + d * 39f - 220f
    Offset(px, py)
}
```

---

## 📸 Screenshots

| Alien life | Spiral Galaxy | Chaos Bloom |
|:---:|:---:|:---:|
| ![Alien](screenshots/alien.gif) | ![Galaxy](screenshots/galaxy.gif) |  ![Bloom](screenshots/bloom.gif) |

---




## Custom parser & dynamic expressions

To facilitate creating custom functions, we are working on a lightweight custom parser capable of interpreting basic math expressions:

- Supported functions : `sin`, `cos`, `tan`, `hypot`, operators `+`, `-`, `*`, `/`
- Available variables : `i` (point index), `t` (time), `size` (canvas size)
- Example editable expression:

```kotlin
(4 + sin(y*2 - t)*3)*cos(x/29)
```

This parser will convert strings into dynamic Kotlin lambdas `(i, t, size) -> Offset`, ready for use in Compose rendering.

If needed, we may integrate libraries like `exp4j` or `mXparser`  to support more complex expressions.

This approach will enable a live editor where users can write, test, and save their own visual formulas in real time.

---

## 🔄 One-liner functions / visual code golf

In the minimalist and creative spirit of the project, we plan a feature that:

- Converts Kotlin functions into compact one-liner strings ready to copy-paste
- Makes sharing and reusing formulas easy within the editor
- Ensures a readable, usable format similar to Processing/p5.js snippets
- Enables sharing visual artworks through simple lines of code
  
---

## 🚧 Roadmap

- Functional base with animated Canvas
- String expression evaluator like p5.js
- Dynamic sliders for parameters
- PNG export
- Preset gallery
- UI for composing/editing live functions

---

## 🔧 Technical dependencies

- Kotlin  
- Jetpack Compose (Canvas, Animation, UI)  
- (Upcoming) Math expression evaluation library (exp4j, mXparser, or custom engine)

---

## 🎯 Secondary goals

- Explore CPU and GPU limits of Compose vector rendering on mobile
- Provide a visual sandbox for creating diverse algorithmic artworks
- Showcase expressive, minimalist mathematical functions
- Experiment with the fusion of code golf and generative art on Android

---
 
## ⚠️ Project status

KArtliner is still a work in progress. 

The rendering engine is fully set up, and the next major step is integrating the custom parser for dynamic math expressions.

This is challenging `because Kotlin does not natively support easy runtime execution of injected code strings`.

To work around this, i am developing a lightweight custom parser that translates math expression strings into executable lambdas.
This approach is quite tricky to implement but crucial for enabling live editing and dynamic function evaluation within the app.

---


## 🧠 Inspirations

- [Processing](https://processing.org/)  
- [p5.js](https://p5js.org/)  
- [ShaderToy](https://www.shadertoy.com/)  
- [Code Golf Challenges](https://codegolf.stackexchange.com/)  
- Visualisation algorithmique et sketches paramétriques

---




