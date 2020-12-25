# LCE
Data type library that describes loading, content and error states.

| Type      | Loading | Content | Error |
| ------------ | -------- | ------- | --------- |
| `LCE<L, C, E>` | Loading  | Content | Error     |
| `UCT<C>`       | Unit     | Content | Throwable |
| `CE<C, E>`     | -        | Content | Error     |
| `CT<C>`        | -        | Content | Throwable |
| `UC<C>`        | Unit     | Content | -         |


## Types
`LCE<LoadingT, ContentT, ErrorT>` is the most flexible type. It allows you to
specify the generic parameter for each state.
```kotlin
val loading = LCE.loading("We are loading")
val content = LCE.content("Hello world")
val error = LCE.error(RuntimeException("boo"))
```

In most cases, we don't need a generic parameter for loading state and we use `Throwable` for
the error type. To support this use case, we have `UCT<C>` which stands for Unit / Content / Throwable.
```kotlin
val loading = UCT.loading()
val content = UCT.content("Hello world")
val error = UCT.error(RuntimeException("boo"))
```

In some scenarios, we don't care about loading and want to represent only content and error states.
There are two types to support this use case. If you represent your error as Throwable, use
the `CT<C>` type which stands for Content / Throwable.
```kotlin
val content = CT.content("Hello world")
val error = CT.error(RuntimeException("boo"))
```

If you want to have a custom type for error, use the `CE` type which stands for Content / Error.
```kotlin
val content = CE.content("Hello world")
val error = CE.error("My custom error message")
```

If you want to support loading and content states without an error type, use `UC` type which
stands for Unit / Content.
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
You can also map error fr

TODO:
- Useful methods such as mapError
- RxJava3 extensions

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
