
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
