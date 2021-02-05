# Changelog
## [0.3.0] - February 5, 2021
- Added `UC.merge()` extension method.
- Added `LC.merge()` extension method.
- Added `UCT.asUC { throwable -> UC.loading }` extension method.
- Added `doOnEvent` extension to `Observable<CE>` type.
- Added `doOnContent` extension to `Observable<CE>` type.
- Added `flatten` extension method to `LC` and `UC` types.
- Added `asUCT()` extension to `UCE<C, Throwable>` type.
- Remove `LCE.error(throwable)`, `UCE.error(throwable)` and `CE.error(throwable)`

## [0.2.0] - January 11, 2021 
- Added LC type.
- Added `foldLoading` extension method to `LCE`, `UCE` and `UCT` types. 
- Added `foldContent` extension method to `LCE`, `UCE` and `UCT` types.
- Added `foldError` extension method to `LCE`, `UCE` and `UCT` types.
- Added `fromNullable` factory method to `LCE`, `UCE`, `UCT`, `LC`, `UC` types.
- Added `fromNullable(content) { [Type].loading() }` factory method to all types.
- Added `contentOrElse {}` extension method to all types.

## [0.1.1] - January 5, 2021
- Added UCE type.
- Added missing extension methods for some of the types. 

## [0.1.0] - December 25, 2020
- Initial types and utilities
