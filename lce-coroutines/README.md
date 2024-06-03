# lce-coroutines
Coroutines/Flow support for LCE. 

If coming over from RxJava, here is the typical mapping

- `switchMapContent` -> `flatMapLatestContent`
- `onlyContentEvents` -> `onlyContentEvents` (same name, with `flatMapMerge` used under the hood)