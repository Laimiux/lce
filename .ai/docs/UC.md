# com.laimiux.lce.UC (Unit / Content)
`UC` is used to represent loading or content. It come from [Lce Library](https:/github.com/Laimiux/lce)
- See `.ai/docs/README.md` for other LCE types.

## Initialization
- Use `UC.loading()` to create loading type
- Use `UC.content(value)` to create content type

## Access state
- There are `isLoading()`, `isContent()` to check the data state
- There are `contentOrNull()` to get the data value

## Transform
- Use `mapContent` to transform the content value
- Use `flatMapContent` to transform content to another LCE type
- Use `fold` to handle all states in a single operation
- Use `foldContent` to handle content individually and other states together.

## Combine with other LCE events
- Use `merge` to combine multiple states together

## Interop with other LCE types
- Use `asUCT` to convert to `UCT`
- Use `asUCE` to convert to `UCE`

## "When" Statement
LCE does not support Kotlin `when(event)` syntax. Instead, use `fold` 
```kotlin
event.fold(
  onLoading = {},
  onContent = {},
)
```

## Testing
Test extension functions and utilities live within `com.laimiux.lce.test` package
- Use `assertLoading()` to verify loading state
- Use `assertContent()` and `assertContent(expectedContent)` to verify and access content state