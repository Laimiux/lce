# LCE testing examples
Test utilities live within `com.laimiux.lce.test` package

## Verifying loading state
```kotlin
event.assertLoading()
```

## Verify content state
Use `assertContent()` to verify content and get access to content value for fine-grained assertions
```kotlin
// Assert content provides a way to access the content value 
val content = event.assertContent().value
Truth.assertThat(content.myProperty).isEqualTo("my-property-value")
```

Use `assertContent(expectedContent)` to verify specific content state
```kotlin
// Can pass expected value to assert content
event.assertContent(expectedContent)
```

## Use `assertError()` and `assertError(expectedError)` to verify and access error state
```kotlin
// Assert error provides a way to access the error value
val error = event.assertError().value

// Can pass expected error to assertError
event.assertError(expectedError)
```