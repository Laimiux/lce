# LCE
Data type library that describes loading, content and error states. To better understand the
concept, read the following [blog post](https://tech.instacart.com/lce-modeling-data-loading-in-rxjava-b798ac98d80).

| Type      | Loading | Content | Error |
| -------------- | -------- | ------- | --------- |
| `LCE<L, C, E>` | Loading  | Content | Error     |
| `UCE<C, E>`    | Unit     | Content | Error     |
| `UCT<C>`       | Unit     | Content | Throwable |
| `CE<C, E>`     | -        | Content | Error     |
| `CT<C>`        | -        | Content | Throwable |
| `LC<L, C>`     | Loading  | Content | -         |
| `UC<C>`        | Unit     | Content | -         |


## Types
`LCE<L, C, E>` is the most flexible type which stands for Loading / Content / Error. It allows you to specify the generic parameter for each state.
```kotlin
val loading = LCE.loading("We are loading")
val content = LCE.content("Hello world")
val error = LCE.error(RuntimeException("boo"))
```

When we don't need a value for loading state, we can use `UCE<C, E>` type which stands for Unit / Content / Error.
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

When we only care about content and error states, we can use `CE<C, E>` type which stands for Content / Error.
```kotlin
val content = CE.content("Hello world")
val error = CE.error("My custom error message")
```

When we use `Throwable` for errors, we can use `CT<C>` type which stands for Content / Throwable.
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
Each type has a fold extension method which allows us to unwrap the data type and handle all of the cases.

For example, this is useful when we want to update the UI based on the current state.
```kotlin
fun render(event: UCT<MyData>) {
  event.fold(
    onLoading = { view.showLoading() },
    onError   = { exception -> view.showError(exception) },
    onContent = { data -> view.showData(data) }
  )
}
```

This is also useful when we want to map to another type.
```kotlin
fun toMessage(event: CT<String>): String {
    return event.fold(
        onContent = { message -> message },
        onError   = { error -> "Something went wrong" }
    )
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
You'll need to add the `lce-rxjava3` dependency to access these features.

### Coroutines Support
Converting a `Flow<T>` operation into `Flow<UCT<T>>`
```kotlin
fun fetchData(): Flow<UCT<MyData>> = flow {
    emit(MyData())
}.toUCT()
```
You'll need to add the `lce-coroutines` dependency to access these features.

### Testing
Use `lce-test` artifact in your tests to simplify making assertions on the `LCE` types.
```kotlin
@Test fun `my test`() {
    val event: LCE<String, String, Throwable> = ...
    // Loading assertions
    event.assertLoading()
    event.assertLoading("loading value")
    
    // Content assertions
    event.assertContent()
    event.assertContent("content value")

    // Error assertions
    event.assertError()
    event.assertError(throwable)
}
```

## Download
Add the library to your list of dependencies:

```groovy
dependencies {
    implementation("com.laimiux.lce:lce:0.4.0")
    // For RxJava 3 support
    implementation("com.laimiux.lce:lce-rxjava3:0.4.0")
    // For Kotlin Coroutines
    implementation("com.laimiux.lce:lce-coroutines:0.4.0")
    // Helper functions for assertions in tests
    implementation("com.laimiux.lce:lce-test:0.4.0")
}
```

## Development
To run tests
```sh
./gradlew :lce:test
./gradlew :lce-rxjava3:test
./gradlew :lce-coroutines:test
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
