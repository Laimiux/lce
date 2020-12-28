# LCE
Data type library that describes loading, content and error states.

| Type      | Loading | Content | Error |
| ------------ | -------- | ------- | --------- |
| `LCE<L, C, E>` | Loading  | Content | Error     |
| `UCE<C, E>`    | Unit     | Content | Error     |
| `UCT<C>`       | Unit     | Content | Throwable |
| `CE<C, E>`     | -        | Content | Error     |
| `CT<C>`        | -        | Content | Throwable |
| `UC<C>`        | Unit     | Content | -         |


## Types
`LCE<L, C, E>` is the most flexible type. It allows you to specify the generic parameter for each state.
```kotlin
val loading = LCE.loading("We are loading")
val content = LCE.content("Hello world")
val error = LCE.error(RuntimeException("boo"))
```

When we don't need a value for loading state, we can use `UCE<C, E>` type which stands for Unit / Content / Throwable.
```kotlin
val loading = UCE.loading()
val content = UCE.content("Hello world")
val error = UCE.error("Something went wrong.")
```

When we use `Throwable` for errors, we can use `UCT<C>` which stands for Unit / Content / Throwable.
```kotlin
val loading = UCT.loading()
val content = UCT.content("Hello world")
val error = UCT.error(RuntimeException("boo"))
```

When we only care about content and error states, we can use `CE<C, E>` type.
```kotlin
val content = CE.content("Hello world")
val error = CE.error("My custom error message")
```

When we use `Throwable` for errors, we can use `CT<C>` type.
```kotlin
val content = CT.content("Hello world")
val error = CT.error(RuntimeException("boo"))
```

When we only care about loading and content states, we can use `UC<C>` which stands for Unit / Content.
```kotlin
val loading = UC.loading()
val content = UC.content("Hello world")
```

### Fold
Fold unwraps the type by forcing you to handle all of its cases. Its useful when we want to update
the UI based on the current state.
```kotlin
fun render(event: UCT<MyData>) {
  event.fold(
    onLoading = { view.showLoading() },
    onError   = { exception -> view.showError(exception) },
    onContent = { data -> view.showData(data) }
}
```

### Map Content
You can map from one content type to another. You can also use this to transform the content
```kotlin
LCE.content("Hello").mapContent {
  "$it World!"
}

LCE.content(0).mapContent { it + 1 }
```

### Map Error
```kotlin
val lce = LCE.error("failure").mapError { message -> RuntimeException(message) }
```

### Type Interop
You can easily convert from one data type to another without incurring any memory allocation.

```kotlin
val uct = LCE.content("Hello World").asUCT()
val lce = uct.asLCE()

// When we go from LCE to CT/CE type, we treat loading state as null
val ct: CT<String>? = lce.asCT()
```

### RxJava 3 Support
Converting a `Single<T>` operation into `Observable<UCT<T>>`
```kotlin
fun fetchData(): Observable<UCT<MyData>> {
    return Single.fromCallable { MyData() }.toUCT()
}
```

## Download
Add the library to your list of dependencies:

```groovy
dependencies {
    implementation 'com.laimiux.lce:lce:0.1.0'
    implementation 'com.laimiux.lce:lce-rxjava3:0.1.0'
}
```

## Development
To run tests
```sh
./gradlew :lce:test
./gradlew :lce-rxjava3:test
```

# License

```
Copyright 2021 Laimonas Turauskas

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
