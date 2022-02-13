# jdbc-ziped-blobs
#ABSTRACT
#RESULTS
| name                               | size    | create        | write         | read          | lob    |
| ---------------------------------- | ------- | ------------- | ------------- | ------------- | ------ |
| FastZippedEntireSerializationTest  | 2       | 0:00:00.01001 | 0:00:00.01187 | 0:00:00.01051 | 372B   |
| FastZippedEntireSerializationTest  | 10      | 0:00:00.01000 | 0:00:00.01052 | 0:00:00.01018 | 647B   |
| FastZippedEntireSerializationTest  | 100     | 0:00:00.01001 | 0:00:00.01043 | 0:00:00.01017 | 3,3kB  |
| FastZippedEntireSerializationTest  | 1000    | 0:00:00.01001 | 0:00:00.01054 | 0:00:00.01028 | 29,3kB |
| FastZippedEntireSerializationTest  | 100000  | 0:00:00.01021 | 0:00:00.01835 | 0:00:00.01644 | 2,9MB  |
| FastZippedEntireSerializationTest  | 1000000 | 0:00:00.01151 | 0:00:09.01530 | 0:00:07.01362 | 28,7MB |
| FastZippedPartialSerializationTest | 2       | 0:00:00.01000 | 0:00:00.01044 | 0:00:00.01025 | 371B   |
| FastZippedPartialSerializationTest | 10      | 0:00:00.01000 | 0:00:00.01041 | 0:00:00.01014 | 644B   |
| FastZippedPartialSerializationTest | 100     | 0:00:00.01000 | 0:00:00.01046 | 0:00:00.01017 | 3,3kB  |
| FastZippedPartialSerializationTest | 1000    | 0:00:00.01000 | 0:00:00.01058 | 0:00:00.01022 | 29,4kB |
| FastZippedPartialSerializationTest | 100000  | 0:00:00.01013 | 0:00:00.01913 | 0:00:00.01627 | 2,9MB  |
| FastZippedPartialSerializationTest | 1000000 | 0:00:00.01161 | 0:00:08.01900 | 0:00:05.01965 | 28,7MB |
| NotZippedEntireSerializationTest   | 2       | 0:00:00.01000 | 0:00:00.01019 | 0:00:00.01017 | 104B   |
| NotZippedEntireSerializationTest   | 10      | 0:00:00.01000 | 0:00:00.01020 | 0:00:00.01018 | 514B   |
| NotZippedEntireSerializationTest   | 100     | 0:00:00.01000 | 0:00:00.01024 | 0:00:00.01024 | 5,2kB  |
| NotZippedEntireSerializationTest   | 1000    | 0:00:00.01000 | 0:00:00.01027 | 0:00:00.01040 | 53,1kB |
| NotZippedEntireSerializationTest   | 100000  | 0:00:00.01012 | 0:00:00.01202 | 0:00:01.01129 | 5,5MB  |
| NotZippedEntireSerializationTest   | 1000000 | 0:00:00.01489 | 0:00:02.01099 | 0:00:10.01380 | 56,1MB |
| NotZippedPartialSerializationTest  | 2       | 0:00:00.01000 | 0:00:00.01021 | 0:00:00.01015 | 109B   |
| NotZippedPartialSerializationTest  | 10      | 0:00:00.01000 | 0:00:00.01053 | 0:00:00.01022 | 498B   |
| NotZippedPartialSerializationTest  | 100     | 0:00:00.01000 | 0:00:00.01155 | 0:00:00.01016 | 5,1kB  |
| NotZippedPartialSerializationTest  | 1000    | 0:00:00.01000 | 0:00:01.01458 | 0:00:00.01027 | 52,1kB |
| NotZippedPartialSerializationTest  | 100000  | 0:00:00.01014 | 0:02:33.01910 | 0:00:01.01310 | 5,4MB  |
| NotZippedPartialSerializationTest  | 1000000 | -:--:--.----- | 0:25:--.----- | -:--:--.----- | 56,1MB |
| ZippedEntireSerializationTest      | 2       | 0:00:00.01000 | 0:00:00.01024 | 0:00:00.01016 | 240B   |
| ZippedEntireSerializationTest      | 10      | 0:00:00.01000 | 0:00:00.01021 | 0:00:00.01015 | 490B   |
| ZippedEntireSerializationTest      | 100     | 0:00:00.01000 | 0:00:00.01027 | 0:00:00.01012 | 3,2kB  |
| ZippedEntireSerializationTest      | 1000    | 0:00:00.01000 | 0:00:00.01061 | 0:00:00.01022 | 29,2kB |
| ZippedEntireSerializationTest      | 100000  | 0:00:00.01012 | 0:00:04.01117 | 0:00:00.01596 | 2,9MB  |
| ZippedEntireSerializationTest      | 1000000 | 0:00:00.01390 | 0:00:41.01378 | 0:00:06.01666 | 28,7MB |
| ZippedPartialSerializationTest     | 2       | 0:00:00.01000 | 0:00:00.01024 | 0:00:00.01019 | 240B   |
| ZippedPartialSerializationTest     | 10      | 0:00:00.01000 | 0:00:00.01024 | 0:00:00.01019 | 508B   |
| ZippedPartialSerializationTest     | 100     | 0:00:00.01000 | 0:00:00.01028 | 0:00:00.01015 | 3,2kB  |
| ZippedPartialSerializationTest     | 1000    | 0:00:00.01000 | 0:00:00.01066 | 0:00:00.01021 | 29,1kB |
| ZippedPartialSerializationTest     | 100000  | 0:00:00.01087 | 0:00:04.01251 | 0:00:00.01607 | 2,9MB  |
| ZippedPartialSerializationTest     | 1000000 | 0:00:00.01134 | 0:00:41.01529 | 0:00:05.01889 | 28,7MB |
#TODO
1. замерить память на сериализацию/десериализацию и архивацию/разархивацию
1. Исправить NotZippedPartialSerializationTest записывает миллион записей 30 мин., проблема в PartialJsonStrategy.
