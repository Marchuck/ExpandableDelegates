
[![](https://img.shields.io/badge/Android%20Arsenal-ExpandableDelegates-green.svg?style=true)](https://android-arsenal.com/details/1/3731)
# ExpandableDelegates
more convenient way to add multiple delegates to your expandable recyclerView! 


<img src="https://github.com/Marchuck/IdentityCrawler/blob/master/screenshot_2.png" height="350"> 
<img src="https://github.com/Marchuck/IdentityCrawler/blob/master/screenshot_1.png" height="350"> 



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

If you have applied this library in your project, contact me, I will add it to the following list:

#Projects using this library:

[Brera Smart District](https://play.google.com/store/apps/details?id=com.comarch.smartcitymilan)

[Be Connected, Be Málaga!](https://play.google.com/store/apps/details?id=com.comarch.smartcity.malaga)


#Authors

This wrapper/library was created by Paweł Próchniak and Łukasz Marczak based on library created by Haruki Hasegawa, who creted [this library](https://github.com/h6ah4i/android-advancedrecyclerview). Thank you!


#License
This library is licensed under the Apache Software License, Version 2.0.


Copyright (C) 2016 Łukasz Marczak, Paweł Próchniak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
