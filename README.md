
[![](https://jitpack.io/v/Marchuck/ExpandableDelegates.svg)](https://jitpack.io/#Marchuck/ExpandableDelegates)

# ExpandableDelegates
more convenient way to add multiple delegates to your expandable recyclerView! 


```groovy
new ExpandableBuilder(this)
                .withAdapter(createAdapter())
                .withRecyclerView(recyclerView)
                .build();
```
#Install
```groovy
  allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

And also

```groovy
  dependencies {
	        compile ('com.github.Marchuck:ExpandableDelegates:v1.0.0'){
	            transitive = true
	        }
	}
```

#Usage

Look at master branch. Code at MainActivity.class will help you.

If you have applied this library in your project, contact me, I'll add it to list.

#Project using this library:

Brera Smart City

#Authors

This wrapper/library was created by Paweł Próchniak and Łukasz Marczak


#License

    The MIT License

    Copyright (c) 2016 Paweł Próchniak, Łukasz Marczak

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
