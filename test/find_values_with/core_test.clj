(ns find-values-with.core-test
  (:require [clojure.test :refer :all]
            [find-values-with.core :refer :all]))

(deftest a-test
  (testing "-main returns parsed JSON when given valid filepath"
    (is (= (-main "test/find_values_with/test-data/US_mini_10000011B1.json")
           ["motor manufactured part" "distortion" "tray" "remaining binder" "apparatus of similar advantage" "print head" "polyjet”" "liquid colloid suspension or powder jetting devices" "layers of second material"]))))
