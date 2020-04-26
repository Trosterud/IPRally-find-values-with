(ns find-values-with.core-test
  (:require [clojure.test :refer :all]
            [cheshire.core :as json]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [find-values-with.core :refer :all]))

(deftest a-test
  (testing "-main Test main with"
    (is (= (-main "test/find_values_with/test-data/base-test.json" "value" "motor")
           ["motorized teleport"]))))


(deftest test-against-all-test-data
  (testing "Test all the JSON objects in test-data/ and its subfolders"

    ;; json-test-data-files are parse from test-data/ and formatted as a LazySeq of MapEntries      
    ;; Parsing works with nested folders as well. If the testing data would ever scale
    ;; A simpler implementation would be: (seq (.list (io/file "./test/find_values_with/test-data")))
    (let [expected-results (json/parse-string (slurp "./test/find_values_with/test-expected-results.json") true)
          json-test-data-files (->> (io/file "./test/find_values_with/test-data")
                                    (file-seq) ; create simple tree of all files with their paths from the root given above
                                    (map #(.getPath %)) ; make a list of all the file-paths
                                    (filter #(str/includes? % ".json")))] ; filter only .json

      (doseq [test-file json-test-data-files]
        (is (=
             (-main test-file "value" "motor")
             (get expected-results (keyword test-file))))))))

