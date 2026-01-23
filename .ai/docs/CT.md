# com.laimiux.lce.CT (Content / Throwable)
`CT` is an LCE type used to represent content or throwable error. It come from [Lce Library](https:/github.com/Laimiux/lce)
- See `.ai/docs/README.md` for other LCE types.

## Initialization
- Use `CT.content(value)` to create content type
- Use `CT.error(throwable)` to create an error type

## Access state
- There are `isContent()`, `isError()` to check the data state
- There are `contentOrNull` and `errorOrNull` to get the data value

## Transform
- Use `mapContent` to transform the content value
- Use `mapError` to transform the error value
- Use `flatMapContent` to transform content to another LCE type
- Use `fold` to handle all states in a single operation
- Use `foldContent` to handle content individually and other states together.

## Combine with other LCE events
- Use `merge` to combine multiple states together
 
## Interop with other LCE types
- Use `asUCT` to convert to `UCT`

## "When" Statement
LCE does not support Kotlin `when(event)` syntax. Instead, use `fold` or `foldContent`
```kotlin
event.fold(
  onContent = {},
  onError = {},  
)
```

## Testing
Test extension functions and utilities live within `com.laimiux.lce.test` package
- Use `assertContent()` and `assertContent(expectedContent)` to verify and access content state
- Use `assertError()` and `assertError(expectedError)` to verify and access error state
