# find-values-with &middot;
> "Returns a vector of the values containing the arg substring,
   from all key's matching the arg key, in the given JSON file."


## Installing / Getting started
A quick introduction of the minimal setup you need to get a hello world up &
running.

```shell
git clone https://github.com/Trosterud/IPRally-find-values-with.git IPRally-find-values-with

cd IPRally-find-values-with/
```

Should work out of the box assuming you have leiningen. 

If you don't the -main function you are looking for is in [src/find_values_with/core.clj](src/find_values_with/core.clj). The only outside dependency is Cheshire for the json decoding. 

## Usage
```
lein repl

(-main "test/find_values_with/test-data/US10000011B1.json"  "value" "motor")

=> ["motorized drive" "three motorized degrees of freedom" "three commanded motors" "servomotor" "lifter motor"]
```


## Tests
```shell
lein test
```
Tests the -main function with all 5 of the json files in /test/find_values_with/test-data/, including an empty json object, and checks results against the corresponding values listed in the JSON file [test-expected-results.json](/test/find_values_with/test-expected-results.json)


## Known limitation/quirks:
- Doesn't validate the JSON in the file given
- Returns [] if no matching values are found
- You need to specify both the key and subtring you are looking for
  - Since this isn't 100% accorgin to specification, feel free to:
    - remove both these input args
    - change the val substring-match to the hardcoded "motor"
    - change 3 occurrences of (keyword key) for :value in -main
- Returns a vector [] like was asked in the assignment but,
  - **I consistently timed it > 10x faster if letting it return a seq () instead of the vector []**
  - In order to implement this just change mapv to map in the final line!

## Some timing on a relatively slow computer

#### Using cheshire/parsed-seq & reader for file reading, returning a seq *("values")
- "Elapsed time: 8.386053 msecs"
- "Elapsed time: 7.41085 msecs"
- "Elapsed time: 7.395079 msecs"
- "Elapsed time: 6.07537 msecs"
- "Elapsed time: 6.744066 msecs"
- "Elapsed time: 9.309233 msecs"

#### Using cheshire/parse-string & slurp for file reading, returning a seq (*"values")
- "Elapsed time: 15.225702 msecs"
- "Elapsed time: 12.959947 msecs"
- "Elapsed time: 11.647658 msecs"
- "Elapsed time: 24.93214 msecs"
- "Elapsed time: 24.412126 msecs"
- "Elapsed time: 15.651322 msecs"

#### Using cheshire/parse-string & slurp for file reading, returning a vector [*"values"]
- "Elapsed time: 37.22464 msecs"
- "Elapsed time: 41.2381 msecs"
- "Elapsed time: 36.477782 msecs"
- "Elapsed time: 35.987973 msecs"
- "Elapsed time: 43.526247 msecs"
- "Elapsed time: 40.898521 msecs"
